package com.sensha4u.springboot.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sensha4u.springboot.camel.service.DiscountService;
import com.sensha4u.springboot.camel.service.ProductService;

@Component
class RestApi extends RouteBuilder {

	@Autowired
	private Environment env;

	//@Override
	public void configure() {
		
		restConfiguration()
			.contextPath("/sensha4u")
//			.component("servlet")
			.apiContextPath("/api-doc")
			.apiProperty("api.title", "Sample Camel REST API")
			.apiProperty("api.version", "1.0")
			.apiProperty("cors", "true")
			.apiContextRouteId("doc-api")
			.port(env.getProperty("server.port", "8080"))
			.host(env.getProperty("myenv.host", "localhost"))
			.bindingMode(RestBindingMode.json);

		rest("/products")
			.description("Details of products")
			.get("ping")
				.description("Ping Test...!")
				.route()
					.routeId("ping-api")
					.setBody(constant("Pong..!"))
				.endRest()	
			.get("/")
				.description("List of all products")
				.route()
					.routeId("product-api")
					.bean(ProductService.class, "listProducts")
				.endRest()
			.get("{id}")
				.description("Product Detail")
				.route()
					.routeId("product")
					.bean(ProductService.class, "findProduct(${header.id})")
				.endRest()
			.get("discounts/{id}")
				.description("Discount of a product")
				.route()
					.routeId("discount-api")
					.bean(DiscountService.class, "findDiscount(${header.id})");
				//.endRest();
	}
}