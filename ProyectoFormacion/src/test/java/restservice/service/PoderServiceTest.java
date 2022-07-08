package restservice.service;

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

import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.repository.PoderRepository;
import restservice.service.impl.PoderServiceImpl;

@ExtendWith(MockitoExtension.class)
class PoderServiceTest {
	
	@InjectMocks
	PoderServiceImpl service;
	
	@Mock
	PoderRepository repository;
	
	private Integer poderId;
	
	@BeforeEach // JUNIT 5
	void setup_test() {
		
		// INIT TEST VARIABLES	
		poderId = Integer.valueOf(1);
	}
	
	@Test
	void test_findAll()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder>expectedResult=new ArrayList<>();
		expectedResult.add(new Poder("Poder1"));
		expectedResult.add(new Poder("Poder2"));
		expectedResult.add(new Poder("Poder3"));
		expectedResult.add(Mockito.mock(Poder.class));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findAll()).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		List<Poder> currentResult=service.findAllPoderes();
		assertThat(currentResult).isNotEmpty();
		
	}
	@Test
	void test_findAllEmpty()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder>expectedResult=new ArrayList<>();
		List<Poder> currentResult=new ArrayList<>();
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findAll()).thenReturn(expectedResult);
		
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult=service.findAllPoderes();
		}
		catch(ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}
		
		
	}
	
	@Test
	void test_findPoder()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Poder> expectedResult=Optional.of(new Poder());
		Optional<Poder> currentResult;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findById(poderId)).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		
		currentResult=service.findPoder(poderId);
		assertThat(currentResult).isNotNull();
	}
	@Test
	void test_findPoderByNamer()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Poder expectedResult=new Poder("Nombre");
		Poder currentResult;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findByNombre("Nombre")).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		
		currentResult=service.findByNombre("Nombre");
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_guardarPoder() {
		Poder poder=new Poder("Poder");
		Poder poderActual=new Poder();
		
		when(repository.save(poder)).thenReturn(poder);
		
		poderActual=service.guardarPoder(poder);
		
		assertThat(poderActual).isNotNull();
	}
	
	
	
}
