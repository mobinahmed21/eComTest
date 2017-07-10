package com.mobin.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mobin.commons.annotations.ApiSortField;
import com.mobin.commons.annotations.ApiSortField.SelfEntity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;


/**
 * Created by hdu on 3/13/17.
 */
public abstract class BaseApiSortableModel {

  /**
   *
   * @return
   */
  @JsonIgnore
  public List<TypedOrder> getApiSortFields() {
    List<Field> fields = getAllFieldsAnnotatedBy(this.getClass(), ApiSortField.class);
    //higher order field will be used first
    Collections.sort(fields, (f1, f2) -> {
      return getFieldOrder(f2) - getFieldOrder(f1);
    });
    return fields.stream().map(field -> createOrderFromField(field)).collect(Collectors.toList());

  }

  /**
   *
   * @param field
   * @return
   */
  protected String getFieldSortName(Field field, ApiSortField sortField) {
    return StringUtils.isNotBlank(sortField.fieldName()) ? sortField.fieldName() : field.getName();
  }

  /**
   *
   * @param field
   * @param sortField
   * @return
   */
  protected String getFieldValueAccessName(Field field, ApiSortField sortField) {
    return StringUtils.isNotBlank(sortField.fieldName()) ? field.getName() + "." + sortField.fieldName()
        : field.getName();
  }

  private int getFieldOrder(Field field) {
    int order = Integer.MAX_VALUE;
    ApiSortField sorfField = AnnotationUtils.getAnnotation(field, ApiSortField.class);
    if (sorfField != null) {
      order = sorfField.order();
    }
    return order;
  }

  private TypedOrder createOrderFromField(Field field) {
    ApiSortField sortField = AnnotationUtils.getAnnotation(field, ApiSortField.class);
    String fieldSortName = getFieldSortName(field, sortField);
    String publicName = StringUtils.isBlank(sortField.publicName()) ? fieldSortName : sortField.publicName();
    String valueAccessName = getFieldValueAccessName(field, sortField);
    Class<?> fieldType = sortField.fieldType().equals(ApiSortField.SameAsAnnotatedField.class) ?
        field.getType() : sortField.fieldType();
    Class<?> entityClass = SelfEntity.class.equals(sortField.entityClass()) ? this.getClass() : sortField.entityClass();

    return new TypedOrder(sortField.direction(), fieldSortName, valueAccessName, publicName, this.getClass(),
        entityClass, fieldType);
  }

  private List<Field> getAllFieldsAnnotatedBy(Class<?> clazz, Class<? extends Annotation> annotationType) {
    List<Field> sortFields = Lists.newArrayList();
    Set<String> addedFieldNames = Sets.newHashSet();
    Field[] fields = FieldUtils.getFieldsWithAnnotation(clazz, annotationType);
    for (Field field : fields) {
      //as FieldUtils.getAllFields will return list of fields which will have the parent fields at the end
      //if has already added the field name, skip parent's field
      if (!addedFieldNames.contains(field.getName())) {
        addedFieldNames.add(field.getName());
        sortFields.add(field);
      }
    }

    return sortFields;
  }


}
