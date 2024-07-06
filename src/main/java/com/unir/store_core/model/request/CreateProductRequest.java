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
	private String id;
	private String name;
	private String category_id;
	private String categoryName;
	private String description;
	private Float price;
	private String image;
	private Integer quantity;
	private Boolean visible;
}
