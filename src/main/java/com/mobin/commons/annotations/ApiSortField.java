package com.mobin.commons.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.data.domain.Sort.Direction;

/**
 * Created by hdu on 3/13/17.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ApiSortField {

  /**
   * sort field name. if empty, can use annotated field name
   */
  public String fieldName() default "";

  /**
   * the data type of the sort field
   */
  public Class<?> fieldType() default SameAsAnnotatedField.class;

  /**
   * sorting direction
   */
  public Direction direction() default Direction.ASC;

  /**
   * order number to be applied. High number will be applied first
   */
  public int order() default 0;

  /**
   * The name used from public side. if empty, will use the field name
   */
  public String publicName() default "";

  /**
   *
   * @return
   */
  public Class<?> entityClass() default SelfEntity.class;

  public static interface SelfEntity {

  }

  public static interface SameAsAnnotatedField {

  }
}
