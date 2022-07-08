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
import restservice.model.Universo;
import restservice.repository.UniversoRepository;
import restservice.service.impl.UniversoServiceImpl;

@ExtendWith(MockitoExtension.class)
class UniversoServiceTest {
	@InjectMocks
	UniversoServiceImpl service;
	
	@Mock
	UniversoRepository repository;
	
	private Integer universoId;
	
	@BeforeEach // JUNIT 5
	void setup_test() {
		
		// INIT TEST VARIABLES	
		universoId = Integer.valueOf(1);
	}
	
	@Test
	void test_findAll()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Universo>expectedResult=new ArrayList<>();
		expectedResult.add(new Universo("Universo1"));
		expectedResult.add(new Universo("Universo2"));
		expectedResult.add(new Universo("Universo3"));
		expectedResult.add(Mockito.mock(Universo.class));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findAll()).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		List<Universo> currentResult=service.findAllUniversos();
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).isNotEmpty();
		
	}
	@Test
	void test_findAllEmpty()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Universo>expectedResult=new ArrayList<>();
		List<Universo> currentResult=new ArrayList<>();
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findAll()).thenReturn(expectedResult);
		
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult=service.findAllUniversos();
		}
		catch(ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}
		
		
	}
	
	@Test
	void test_findUniverso()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Universo> expectedResult=Optional.of(new Universo());
		Optional<Universo> currentResult;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findById(universoId)).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		
		currentResult=service.findUniverso(universoId);
		assertThat(currentResult).isNotNull();
	}
	@Test
	void test_findUniversoByNamer()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Universo expectedResult=new Universo("Nombre");
		Universo currentResult;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findByNombre("Nombre")).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		
		currentResult=service.findByNombre("Nombre");
		assertThat(currentResult).isNotNull();
	}
	
	@Test
	void test_guardarUniverso() {
		Universo poder=new Universo("Universo");
		Universo poderActual=new Universo();
		
		when(repository.save(poder)).thenReturn(poder);
		
		poderActual=service.guardarUniverso(poder);
		
		assertThat(poderActual).isNotNull();
	}
}
