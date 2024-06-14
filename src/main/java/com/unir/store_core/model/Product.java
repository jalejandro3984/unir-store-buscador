package com.unir.store_core.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
	private Long id;
	private Long categoryId;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean visible;
}
