package com.mobin.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CatalogProductDto extends BaseDto {

	private Integer catalogId;
	private Integer productId;
	private Integer quantity;
	private Boolean configurable;
}
