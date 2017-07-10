package com.mobin.dto.mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mobin.dto.converters.ConvertOptions;
import com.mobin.dto.converters.DataConverter;

import lombok.Getter;

@Service
@Getter
@SuppressWarnings({"rawtypes", "unchecked"})
public class DataMapper {

  private final Mapper beanMapper;
  private final Map<String, DataConverter> customDataConverters;

  
  @Autowired
  public DataMapper(Mapper beanMapper, List<DataConverter> customDataConverters) {
    this.beanMapper = beanMapper;
    this.customDataConverters = parseConverters(customDataConverters);
  }

  /**
   *
   * @param entities
   * @param dtoClass
   * @param <E>
   * @param <T>
   * @return
   */
  public <E, T> List<T> convertFromEntitiesToDtos(List<E> entities, Class<T> dtoClass){
    if (CollectionUtils.isEmpty(entities)){
      return Lists.newArrayList();
    }
    DataConverter converter = findConverter(entities.get(0).getClass(), dtoClass);
    if (converter != null) {
      return (List<T>) converter.convertToTargets(entities);
    }
    return mapBeans(entities, dtoClass);
  }
  
  public <E, T> List<T> convertFromEntitiesToDtos(List<E> entities, Class<T> dtoClass, ConvertOptions options){
    if (CollectionUtils.isEmpty(entities)){
      return Lists.newArrayList();
    }
    DataConverter converter = findConverter(entities.get(0).getClass(), dtoClass);
    if (converter != null) {
      return (List<T>) converter.convertToTargets(entities, options);
    }
    return mapBeans(entities, dtoClass);
  }

  /**
   *
   * @param entity
   * @param dtoClass
   * @param <E>
   * @param <T>
   * @return
   */
  public <E, T> T convertFromEntityToDto(E entity, Class<T> dtoClass) {
    return convertFromEntitiesToDtos(Lists.newArrayList(entity), dtoClass).get(0);
  }

  /**
   *
   * @param dtos
   * @param entityClass
   * @param <F>
   * @param <E>
   * @return
   */
  public <F, E> List<E> convertFromDtosToEntities(List<F> dtos, Class<E> entityClass){
    if (CollectionUtils.isEmpty(dtos)){
      return Lists.newArrayList();
    }
    DataConverter converter = findConverter (entityClass, dtos.get(0).getClass());
    if (converter != null) {
      return (List<E>) converter.convertToSources(dtos);
    }
    return mapBeans(dtos, entityClass);
  }


  /**
   *
   * @param dto
   * @param entityClass
   * @param <F>
   * @param <E>
   * @return
   */
  public <F, E> E convertFromDtoToEntity(F dto, Class<E> entityClass) {
    return convertFromDtosToEntities(Lists.newArrayList(dto), entityClass).get(0);
  }

  private <S,T> List<T> mapBeans(List<S> sources, Class<T> targetClass){
    if (CollectionUtils.isEmpty(sources)){
        return Lists.newArrayList();
    }
    return sources.stream().map( s -> {
      T target = (T) ReflectUtils.newInstance(targetClass);
       beanMapper.map(s, target);
      return target;
    }).collect(Collectors.toList());
  }

  private DataConverter findConverter(Class entityClass, Class dtoClass) {
    if (MapUtils.isEmpty(customDataConverters)) {
      return null;
    }
    String key = generateKeyForConverter(entityClass.getName(), dtoClass.getName());
    return customDataConverters.get(key);
  }

  private Map<String, DataConverter> parseConverters(List<DataConverter> converters) {
    Map<String, DataConverter> convertersMap = Maps.newHashMap();
    if (CollectionUtils.isNotEmpty(converters)) {
      converters.forEach(converter -> {
        String key = generateKeyForConverter(generateConverterKey(converter, 0), generateConverterKey(converter, 1));
        convertersMap.put(key, converter);
      });
    }
    return convertersMap;
  }

  private String generateConverterKey(DataConverter dataConverter, int index) {
    //by now, just support find the converter that implement DataConverter interface without generic. for example:
    // class NotSupportedConverter <S,T> implement DataConverter<S,T>
    if (dataConverter.getClass().getGenericSuperclass() instanceof ParameterizedType) {
      Type type = ((ParameterizedType) dataConverter.getClass().getGenericSuperclass()).getActualTypeArguments()[index];
      if (type instanceof Class) {
        // for class that implements generic like public class FlexRenditionClient extends FlexAssetClient <Rendition>
        return ((Class) type).getName();
      }
    }
    throw new IllegalArgumentException(
        dataConverter.getClass() + "does not implement generic interface with source and target");
  }

  private String generateKeyForConverter(String part1, String part2){
    return part1 + "-" + part2;
  }
}
