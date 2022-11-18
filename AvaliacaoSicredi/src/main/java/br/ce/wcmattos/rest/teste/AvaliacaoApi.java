package br.ce.wcmattos.rest.teste;
import static io.restassured.RestAssured.given;

import java.util.Random;

import org.junit.Test;
import br.ce.wcmattos.rest.core.TesteBasico;



public class AvaliacaoApi extends TesteBasico {
	
	long cpfRestrito = 60094146012l;
	long cpfNormal= 123456789010l;
	long cpfExistente = 97093236014l;
	
	
	Random gerador = new Random();
	
	long cpfNovo = gerador.nextLong(10000000000l);
	
	@Test
	public void consultarCpfRestricao() {
		given()
		.log().all()
		.when()
			.get("/restricoes/"+cpfRestrito)
		.then()
			.statusCode(200)
			
	    ;
	}
	@Test
	public void consultaCpfNormal() {
		given()
		.log().all()
		.when()
			.get("/restricoes/"+cpfNormal)
		.then()
			.statusCode(204)
			
	    ;
		
	}
	@Test
	public void iserirSimulacao() {
					
		given()
		.log().all()
		.contentType(APP_CONTENT_TYPE)
		.body("{\"cpf\": \"" + cpfNovo + "\", \"nome\": \"Teste Nome\", \"email\": \"teste@gmail.com\", \"valor\": \"1000\", \"parcelas\": \"2\", \"seguro\": \"true\"}")
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(201)
			
	    ;
	}
	@Test
	public void iserirSimulacaoDuplicada() {
		given()
		.log().all()
		.contentType(APP_CONTENT_TYPE)
		.body("{\"cpf\": " + cpfExistente + ", \"nome\": \"Teste Nome\", \"email\": \"teste@gmail.com\", \"valor\": \"1000\", \"parcelas\": \"2\", \"seguro\": \"true\"}")
		.when()
			.post("/simulacoes/"+cpfExistente)
		.then()
			.statusCode(405)
			
	    ;

	}
	@Test
	public void iserirSimulacaoIncorreta() {
		given()
		.log().all()
		.contentType(APP_CONTENT_TYPE)
		.body("{\"cpf\": " + cpfNovo + ", \"no\": \"Teste Nome\", \"email\": \"teste@gmail.com\", \"valor\": \"1000\", \"parcelas\": \"2\", \"seguro\": \"true\"}")
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400)
			
	    ;

	}
	@Test
	public void alterarSimulacaoNaoEncontrada() {
		given()
		.log().all()
		.body("{\"cpf\": " + cpfNovo + ", \"no\": \"Teste Nome\", \"email\": \"teste@gmail.com\", \"valor\": \"1000\", \"parcelas\": \"2\", \"seguro\": \"true\"}")
		.when()
			.put("/simulacoes/"+cpfNovo)
		.then()
			.statusCode(404)
			
	    ;
	}
	@Test
	public void alterarSimulacao() {
		given()
		.log().all()
		.body("{\"cpf\": " + cpfExistente + ", \"nome\": \"Teste Nome\", \"email\": \"teste@gmail.com\", \"valor\": \"1000\", \"parcelas\": \"2\", \"seguro\": \"true\"}")
		.when()
			.put("/simulacoes/"+cpfExistente)
		.then()
			.statusCode(200)
			
	    ;
		
	}
	@Test
	public void consultarTodasSimulacoes() {
		given()
		.log().all()
		.when()
			.get("/simulacoes/")
		.then()
			.statusCode(200)
			
	    ;
	}
	@Test
	public void consultarSimulacoesCpf() {
		given()
		.log().all()
		.when()
			.get("/simulacoes/"+cpfExistente)
		.then()
			.statusCode(200)
			
	    ;
	}
	@Test
	public void consultarCpfSemSimulacao() {
		given()
		.log().all()
		.when()
			.get("/simulacoes/"+cpfNovo)
		.then()
			.statusCode(404)
			
	    ;
	}
	@Test
	public void removerSimulacao() {
		given()
		.log().all()
		.when()
			.delete("/simulacoes/"+ 1)
		.then()
			.statusCode(200)
			
	    ;
	}
}
