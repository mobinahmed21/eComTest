package com.mobin.dto.models;


import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductDto extends BaseDto {

	private String name;
	private String description;
}
