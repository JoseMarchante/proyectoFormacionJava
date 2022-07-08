package restservice.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import restservice.ProyectoFormacionApplication;
import restservice.model.Poder;
import restservice.model.Superheroe;
import restservice.model.Universo;

@SpringBootTest(classes = ProyectoFormacionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RestServiceIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String getRootUrl() {
		return "http://localhost:8080/api/v1";
	}
	//LLAMADAS A SUPERHEROE
	
	@Test
	void testGetAllSuperheroes() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/superheroes",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	void testCreateSuperheroe() {
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Superheroe newSuperheroe = new Superheroe("Nombre Superheroe",true,new Universo("Universo"),poderes);

		ResponseEntity<Superheroe> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/createSuperheroe", newSuperheroe, Superheroe.class);
		assertNotNull(postResponse);
	}
	
	@Test
	void testGetSuperheroeById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/superheroes/1",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());	
	}
	
	@Test
	void testGetSuperheroeByName() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/superheroesByName/nombre",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testDeleteSuperheroeById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/superheroes/delete/1",
				HttpMethod.POST, entity, String.class);
		
		assertNotNull(response);
		
	}
	
	@Test
	void testUpdateSuperheroe() {
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Superheroe newSuperheroe = new Superheroe("Nombre Superheroe",true,new Universo("Universo"),poderes);

		ResponseEntity<Superheroe> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/superheroes/update/1", newSuperheroe, Superheroe.class);
		assertNotNull(postResponse);
	}
	
	@Test
	void testMatarSuperheroe() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/superheroes/matar/1",
				HttpMethod.POST, entity, String.class);
		
		assertNotNull(response);
	}
	@Test
	void testRevivirSuperheroe() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/superheroes/revivir/1",
				HttpMethod.POST, entity, String.class);
		
		assertNotNull(response);
	}
	
	@Test
	void testAgregarPoderSuperheroe() {
		ResponseEntity<Superheroe> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/superheroes/agregarPoder/1", 1, Superheroe.class);
		assertNotNull(postResponse);
	}
	@Test
	void testEliminarPoderSuperheroe() {
		ResponseEntity<Superheroe> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/superheroes/eliminarPoder/1", 1, Superheroe.class);
		assertNotNull(postResponse);
	}
	
	//LLAMADAS A PODERES//////////////////////////////////////////
	@Test
	void testGetAllPoderes() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/poderes",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testCreatePoder() {
		Poder newPoder = new Poder("Nombre Poder");

		ResponseEntity<Poder> postResponse = restTemplate.postForEntity(getRootUrl() + "/createPoder", newPoder, Poder.class);
		assertNotNull(postResponse);
	}
	
	@Test
	void testGetPoderById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/poderes/1",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testDeletePoderById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/poderes/delete/1",
				HttpMethod.POST, entity, String.class);
		
		assertNotNull(response.getBody());
		
	}
	
	@Test
	void testUpdatePoder() {
		
		Poder newPoder=new Poder("Nombre");
		ResponseEntity<Poder> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/poderes/update/1", newPoder, Poder.class);
		assertNotNull(postResponse);
	}
	//LLAMADAS A UNIVERSOS//////////////////////////////////////////
	
	@Test
	void testGetAllUniversos() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/universos",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
	@Test
	void testCreateUniverso() {
		Universo newUniverso = new Universo("Nombre Universo");

		ResponseEntity<Universo> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/createUniverso", newUniverso, Universo.class);
		assertNotNull(postResponse);
	}
	
	
	
	@Test
	void testGetUniversoById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/universos/1",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testDeleteUniversoById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate
				.exchange(getRootUrl() + "/universos/delete/1",
				HttpMethod.POST, entity, String.class);
		
		assertNotNull(response.getBody());
		
	}
	

	@Test
	void testUpdateUniverso() {
		Universo newUniverso=new Universo("Nombre");
		ResponseEntity<Universo> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/universos/update/1", newUniverso, Universo.class);
		assertNotNull(postResponse);
	}
	
	

	
}
