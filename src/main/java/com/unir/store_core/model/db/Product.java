package com.unir.store_core.model.db;



import com.unir.store_core.utils.Const;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "employees", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
	@Id
	private Long id;

	@Field(type = FieldType.Keyword, name = Const.FIELD_CATEGORY)
	private String categoryId;

	@Field(type = FieldType.Text, name = Const.FIELD_NAME)
	private String name;

	@Field(type = FieldType.Search_As_You_Type, name = Const.FIELD_DESCRIPTION)
	private String description;

	@Field(type = FieldType.Double, name = Const.FIELD_PRICE)
	private Double price;

	@Field(type = FieldType.Boolean, name = Const.FIELD_AVAILABLE)
	private Boolean visible;
}
