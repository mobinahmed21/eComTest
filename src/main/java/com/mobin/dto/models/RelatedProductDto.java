package com.mobin.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RelatedProductDto extends BaseDto {

	private Integer prodcutId;
	private Integer parentProductId;
}
