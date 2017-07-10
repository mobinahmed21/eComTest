package com.mobin.dto.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductPriceDto extends BaseDto{

	private Integer productId;
	private Integer price;
}
