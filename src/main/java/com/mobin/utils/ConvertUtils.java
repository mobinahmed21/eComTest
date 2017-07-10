package com.mobin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

/**
 * Created by Mobin_Mohammad on 6/7/2017.
 */
public class ConvertUtils {

  /**
   * Converts into Map with given Function as key and value as object.
   */
  public static <K, T> Map<K, T> convertToMap(List<T> objects, Function<T, K> keyFunction) {
    Map<K, T> result = Maps.newHashMap();
    if (CollectionUtils.isEmpty(objects)) {
      return result;
    }
    objects.forEach(o -> result.put(keyFunction.apply(o), o));
    return result;
  }

  /**
   * Converts into Map with given Function as key and value as list of objects.
   */
  public static <K, T> Map<K, List<T>> convertToMapWithList(List<T> objects, Function<T, K> keyFunction) {
    Map<K, List<T>> result = Maps.newHashMap();
    if (CollectionUtils.isEmpty(objects)) {
      return result;
    }
    for (T object : objects) {
      List<T> objectList = result.getOrDefault(keyFunction.apply(object), new ArrayList<>());
      objectList.add(object);
      result.put(keyFunction.apply(object), objectList);
    }
    return result;
  }

}
