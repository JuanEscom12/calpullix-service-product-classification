package com.calpullix.service.product.classification.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.calpullix.service.product.classification.model.ProductClassifyRequest;
import com.calpullix.service.product.classification.model.ProductClassifyResponse;
import com.calpullix.service.product.classification.model.ProductResponse;
import com.calpullix.service.product.classification.util.AbstractWrapper;
import com.calpullix.service.product.classification.util.ValidationHandler;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ProductClassifyHandler {

	@Value("${app.message-error-location-body}")
	private String messageErrorLocationBody;

	@Autowired
	private ValidationHandler validationHandler;


	@Timed(value = "calpullix.service.product.classify.metrics", description = "Retriving products to classify")
	public Mono<ServerResponse> getProductsToClassify(ServerRequest serverRequest) {
		log.info(":: Get Products to Classify handler {} ", serverRequest);
		return validationHandler.handle(
				input -> input.flatMap(request -> AbstractWrapper.async(() -> getResult()))
						.flatMap(response -> ServerResponse.ok().body(BodyInserters.fromObject(response))),
				serverRequest, ProductClassifyRequest.class).switchIfEmpty(Mono.error(new Exception(messageErrorLocationBody)));
	}

	@Timed(value = "calpullix.service.product.classify.metrics", description = "Updating products to classify")
	public Mono<ServerResponse> updateProductsToClassify(ServerRequest serverRequest) {
		log.info(":: Update Products to Classify handler {} ", serverRequest);
		return validationHandler.handle(
				input -> input.flatMap(request -> AbstractWrapper.async(() -> getResult()))
						.flatMap(response -> ServerResponse.ok().body(BodyInserters.fromObject(response))),
				serverRequest, ProductClassifyRequest.class).switchIfEmpty(Mono.error(new Exception(messageErrorLocationBody)));
	}
	

	private ProductClassifyResponse getResult() {
		final ProductClassifyResponse result = new ProductClassifyResponse();
		final List<ProductResponse> list = new ArrayList<>();
		ProductResponse item;
		for(int index = 0; index < 5; index++) {
			item = new ProductResponse();
			item.setId(index);
			item.setContent(
					    "\nNombre                                                  Producto X \n"
					  + "\nDescripcion                                             Descripcion\n"
					  + "\nMarca                                                   Marca YYY  \n"
					  + "\nTamaño                                                 25 X 10 X 12\n"
					  + "\nColor                                                   Amarillo   \n"
					  + "\nMaterial                                                Material   \n"
					  + "\nPrecio                                                  125.00 $   \n"
					  + "\nClasificaion:                                           2          \n");
			item.setName("Producto X");
			item.setDescription("Descripcion");
			item.setBrand("Marca YYY");
			item.setSize(new BigDecimal(199.12));
			item.setColor("Amarillo");
			item.setMaterial("Material");
			item.setPrice(new BigDecimal(125.00));
			list.add(item);
		} 
		
		result.setProductsClassify(list);
		return result;
	}
	
	
	
}
