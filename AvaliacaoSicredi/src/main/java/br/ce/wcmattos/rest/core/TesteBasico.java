package br.ce.wcmattos.rest.core;

import org.junit.BeforeClass;

import java.util.Random;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class TesteBasico implements Constantes {
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = APP_BASE_URL;
		RestAssured.port = APP_PORT;
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(APP_CONTENT_TYPE);
		RestAssured.requestSpecification = reqBuilder.build();
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
			
	}	
}
