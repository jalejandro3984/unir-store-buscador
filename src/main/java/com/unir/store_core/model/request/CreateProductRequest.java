package com.unir.store_core.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
	private Integer id;
	private String name;
	private String categoryId;
	private String description;
	private Double price;
	private String image;
	private Integer quantity;
	private Boolean visible;
}
