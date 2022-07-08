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

import restservice.dto.UniversoDTO;
import restservice.exception.ResourceNotFoundException;
import restservice.model.Universo;
import restservice.service.UniversoService;

@ExtendWith(MockitoExtension.class)
class UniversoControllerTest {
	@InjectMocks
	UniversoController controller;
	
	@Mock
	UniversoService service;
	
	private Integer universoId;
	
	@BeforeEach // JUNIT 5
	void setup_test() {
		
		// INIT TEST VARIABLES	
		universoId = Integer.valueOf(1);
	}
	
	@Test
	void test_universos() throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Universo> expectedResult=new ArrayList<>();
		expectedResult.add(new Universo("Universo"));
		expectedResult.add(new Universo("Universito2"));
		expectedResult.add(Mockito.mock(Universo.class));
		expectedResult.add(Mockito.mock(Universo.class));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllUniversos()).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		List<String> currentResult=controller.universos();
		
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_universos_KO() throws Exception {
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<String> currentResult=new ArrayList<>();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllUniversos()).thenThrow(new ResourceNotFoundException("No existen universos en la base de datos!"));		
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult = controller.universos();
			
			
		} catch (ResourceNotFoundException e) {
			assertThat(currentResult).isEmpty();
		}
	}
	@Test
	void test_universos_empty() throws Exception {
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Universo> expectedResult=new ArrayList<>();
		List<String> currentResult=new ArrayList<>();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllUniversos()).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existen universos en la base de datos!"));
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
		currentResult = controller.universos();
		}
		catch(ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}
		
	}
	@Test
	void test_universo_id() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Universo> expectedResult=Optional.of(new Universo("Nombre"));
		String currentResult="";
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findUniverso(universoId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.getUniversoById(universoId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_universo_idNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Universo> expectedResult=Optional.empty();
		String currentResult;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findUniverso(universoId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existen poderes en la base de datos!"));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.getUniversoById(universoId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		
	}
	@Test
	void test_create_universo() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS

		Universo expectedResult=new Universo("Nombre");
		UniversoDTO currentResult=new UniversoDTO(new Universo("Nombre"));
		
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		controller.crearUniverso(currentResult);
		service.guardarUniverso(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		assertThat(currentResult).isNotNull();
		assertThat(expectedResult).isNotNull();
	}
	@Test
	void test_delete_universo_id() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Universo> expectedResult = Optional.of(new Universo("Nombre"));
		ResponseEntity<UniversoDTO> currentResult = null;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findUniverso(universoId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult=controller.deleteUniversoById(universoId);
		} catch (ResourceNotFoundException w) {
			assertThat(currentResult).isNull();
		}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotNull();
	}
	@Test
	void test_delete_universo_idNull() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Universo> expectedResult = Optional.empty();
		ResponseEntity<UniversoDTO> currentResult = null;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findUniverso(universoId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult=controller.deleteUniversoById(universoId);
		} catch (ResourceNotFoundException w) {
			assertThat(currentResult).isNull();
		}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNull();
	}
}
