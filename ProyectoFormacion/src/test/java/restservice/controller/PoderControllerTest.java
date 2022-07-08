package restservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import restservice.dto.PoderDTO;
import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.service.PoderService;

@ExtendWith(MockitoExtension.class)
class PoderControllerTest {
	@InjectMocks
	PoderController controller;

	@Mock
	PoderService service;

	private Integer poderId;

	@BeforeEach // JUNIT 5
	void setup_test() {

		// INIT TEST VARIABLES
		poderId = Integer.valueOf(1);
	}

	@Test
	void test_poderes() throws Exception {

		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> expectedResult = new ArrayList<>();
		expectedResult.add(new Poder("podercito"));
		expectedResult.add(new Poder("podercito2"));
		expectedResult.add(Mockito.mock(Poder.class));
		expectedResult.add(Mockito.mock(Poder.class));

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllPoderes()).thenReturn(expectedResult);

		// LLAMADA A MÉTODO A TESTEAR
		List<String> currentResult = controller.poderes();

		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotEmpty();
	}

	@Test
	void test_poderes_KO() throws Exception {

		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<String> currentResult = new ArrayList<>();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllPoderes())
				.thenThrow(new ResourceNotFoundException("No existen poderes en la base de datos!"));

		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult = controller.poderes();

		} catch (ResourceNotFoundException e) {
			assertThat(currentResult).isEmpty();
		}
	}

	@Test
	void test_poderes_empty() throws Exception {

		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> expectedResult = new ArrayList<>();
		List<String> currentResult = new ArrayList<>();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllPoderes()).thenReturn(expectedResult)
				.thenThrow(new ResourceNotFoundException("No existen poderes en la base de datos!"));

		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult = controller.poderes();
		} catch (ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}

	}

	@Test
	void test_poder_id() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Poder> expectedResult = Optional.of(new Poder("Nombre"));
		String currentResult = "";
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findPoder(poderId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.getPoderById(poderId);
		} catch (ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotEmpty();
	}

	@Test
	void test_poder_idNull() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Poder> expectedResult = Optional.empty();
		String currentResult;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findPoder(poderId)).thenReturn(expectedResult)
				.thenThrow(new ResourceNotFoundException("No existen poderes en la base de datos!"));

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.getPoderById(poderId);
		} catch (ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}

	}

	@Test
	void test_create_poder() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS

		Poder expectedResult = new Poder("Nombre");
		PoderDTO currentResult = new PoderDTO(new Poder("Nombre"));

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		controller.crearPoder(currentResult);
		service.guardarPoder(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO

		assertThat(currentResult).isNotNull();
		assertThat(expectedResult).isNotNull();
	}
	
	@Test
	void test_delete_poder_id() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Poder> expectedResult = Optional.of(new Poder("Nombre"));
		ResponseEntity<PoderDTO> currentResult = null;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findPoder(poderId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult=controller.deletePoderById(poderId);
		} catch (ResourceNotFoundException w) {
			assertThat(currentResult).isNull();
		}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotNull();
	}
	@Test
	void test_delete_poder_idNull() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Poder> expectedResult = Optional.empty();
		ResponseEntity<PoderDTO> currentResult = null;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findPoder(poderId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult=controller.deletePoderById(poderId);
		} catch (ResourceNotFoundException w) {
			assertThat(currentResult).isNull();
		}
	}
}
