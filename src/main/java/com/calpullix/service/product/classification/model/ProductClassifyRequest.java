package com.calpullix.service.product.classification.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClassifyRequest {
	
	private Integer page;
	
	private List<Integer> productsToClassify;

}
