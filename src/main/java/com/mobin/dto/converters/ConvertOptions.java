package com.mobin.dto.converters;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by Mobin_Mohammad on 6/9/2017.
 */
@Setter
@Getter
public class ConvertOptions {

  private Set<String> include;
  private Set<String> exclude;


  public ConvertOptions() {
    this.include = Sets.newHashSet();
    this.exclude = Sets.newHashSet();
  }

  public boolean isIncluded(String optionName) {
    return CollectionUtils.isNotEmpty(this.include) && this.include.contains(optionName);
  }

  public boolean isNotExcluded(String optionName) {
    return CollectionUtils.isEmpty(this.exclude) || !this.exclude.contains(optionName);
  }
}
