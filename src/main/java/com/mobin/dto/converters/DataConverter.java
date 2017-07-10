package com.mobin.dto.converters;

import java.util.List;

public interface DataConverter<S, T, O> {

  /**
   *
   * @param sources
   * @return
   */
  public List<T> convertToTargets(List<S> sources);

  /**
   *
   * @param source
   * @return
   */
  public T convertToTarget(S source);

  /**
   *
   * @param sources
   * @return
   */
  public List<T> convertToTargets(List<S> sources, O options);

  /**
   *
   * @param source
   * @return
   */
  public T convertToTarget(S source, O options);

  /**
   *
   * @param target
   * @return
   */
  public S convertToSource(T target);

  /**
   *
   * @param targets
   * @return
   */
  public List<S> convertToSources(List<T> targets);
}
