package br.ce.wcmattos.rest.core;

import io.restassured.http.ContentType;

public interface Constantes {
	String APP_BASE_URL = "http://localhost:8080/api/v1/";
	Integer APP_PORT = 8080;
	
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
}
