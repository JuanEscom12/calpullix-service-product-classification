package com.calpullix.service.product.classification.conf;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.calpullix.service.product.classification.handler.ProductClassifyHandler;

@Configuration
public class RoutesConf {

	@Value("${app.path-get-products-classify}")
	private String pathGetProductsToClassify;
	
	@Value("${app.path-update-products-classify}")
	private String pathUpdateProductsToClassify;
	
	

	@Bean
	public RouterFunction<ServerResponse> routesLogin(ProductClassifyHandler loginHandler) {
		return route(POST(pathGetProductsToClassify), loginHandler::getProductsToClassify)
				.and(route(POST(pathUpdateProductsToClassify), loginHandler::updateProductsToClassify));
	}
	
}
