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

@Document(indexName = "products", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
	@Id
	@org.springframework.data.annotation.Id
	@Field(type = FieldType.Keyword, name = Const.FIELD_ID)
	private String id;

	@Field(type = FieldType.Text, name = Const.FIELD_CATEGORY)
	private String categoryName;

	@Field(type = FieldType.Text, name = Const.FIELD_NAME)
	private String name;

	@Field(type = FieldType.Text, name = Const.FIELD_DESCRIPTION)
	private String description;

	@Field(type = FieldType.Float, name = Const.FIELD_PRICE)
	private Float price;

	@Field(type = FieldType.Text, name = Const.FIELD_IMAGE)
	private String image;

	@Field(type = FieldType.Integer, name = Const.FIELD_QUANTITY)
	private Integer quantity;

	@Field(type = FieldType.Boolean, name = Const.FIELD_VISIBLE)
	private Boolean visible;

	@Field(type = FieldType.Keyword, name = Const.FIELD_CATEGORY_ID)
	private String category_id;

	@Field(type = FieldType.Date, name = Const.FIELD_DATE)
	private String date;

}
