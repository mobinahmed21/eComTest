package com.mobin.commons.models;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * Created by hdu on 3/13/17.
 */
@Getter
@ToString(callSuper = true)
public class TypedOrder extends Order {

  private final Class<?> declaringClass;
  private final Class<?> orderFieldType;
  private final String publicName;
  /**
   * this one is used to extract order offset value from result data
   * it can be like provider.id, means from the current object, get id value from current object provider field
   */
  private final String valueAccessProperty;
  private final Class<?> entityClass;

  public TypedOrder(Direction direction, String property, String valueAccessProperty, String publicName, Class<?> declaringClass,
      Class<?> entityClass, Class<?> orderFieldType) {
    super(direction, property);
    this.valueAccessProperty = valueAccessProperty;
    this.publicName = publicName;
    this.declaringClass = declaringClass;
    this.entityClass = entityClass;
    this.orderFieldType = orderFieldType;
  }
}
