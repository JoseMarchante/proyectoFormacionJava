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
import restservice.model.Superheroe;
import restservice.model.Universo;
import restservice.repository.SuperheroeRepository;
import restservice.service.impl.SuperheroeServiceImpl;

@ExtendWith(MockitoExtension.class)
class SuperheroeServiceTest {
	@InjectMocks
	SuperheroeServiceImpl service;
	
	@Mock
	SuperheroeRepository repository;
	
	private Integer superheroeId;
	
	@BeforeEach // JUNIT 5
	void setup_test() {
		
		// INIT TEST VARIABLES	
		superheroeId = Integer.valueOf(1);
	}
	
	@Test
	void test_findAll()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Superheroe>expectedResult=new ArrayList<>();
		expectedResult.add(new Superheroe("Poder1"));
		expectedResult.add(new Superheroe("Poder2"));
		expectedResult.add(new Superheroe("Poder3"));
		expectedResult.add(Mockito.mock(Superheroe.class));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findAll()).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		List<Superheroe> currentResult=service.findAllSuperheroes();
		assertThat(currentResult).isNotEmpty();
		
	}
	@Test
	void test_findAllEmpty()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Superheroe>expectedResult=new ArrayList<>();
		List<Superheroe> currentResult=new ArrayList<>();
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findAll()).thenReturn(expectedResult);
		
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult=service.findAllSuperheroes();
		}
		catch(ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}
		
		
	}
	
	@Test
	void test_findSuperheroe()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Superheroe> expectedResult=Optional.of(new Superheroe());
		Optional<Superheroe> currentResult;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findById(superheroeId)).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		
		currentResult=service.findSuperheroe(superheroeId);
		assertThat(currentResult).isNotEmpty();
	}
	@Test
	void test_findSuperheroeByName()throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Superheroe> expectedResult=new ArrayList<>();
		expectedResult.add(new Superheroe("Nombre"));
		List<Superheroe> currentResult;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(repository.findByNombreContaining("Nombre")).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		
		currentResult=service.findByNameContaining("Nombre");
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_guardarSuperheroe() {
		Superheroe superheroe=new Superheroe("Superheroe");
		Superheroe superheroeActual=new Superheroe();
		
		when(repository.save(superheroe)).thenReturn(superheroe);
		
		superheroeActual=service.guardarSuperheroe(superheroe);
		
		assertThat(superheroeActual).isNotNull();
	}
	
	@Test
	void test_matarSuperheroe() {
		Superheroe superheroe=new Superheroe("Nombre");
		
		service.morir(superheroe);
		assertThat(superheroe.getEstado()).isFalse();
		
	}
	
	@Test
	void test_revivirSuperheroe() {
		Superheroe superheroe=new Superheroe("Nombre");
		
		service.revivir(superheroe);
		assertThat(superheroe.getEstado()).isTrue();
		
	}
	
	@Test
	void test_agregarPoderSuperheroe() throws Exception{
		List<Poder>poderes=new ArrayList<>();
		poderes.add(new Poder("Nombre"));
		Optional<Superheroe> expectedResult=Optional.of(new Superheroe("Nombre",true,new Universo("Nombre"),poderes));
		Optional<Superheroe> currentResult;
		
		Poder poder=new Poder("NombrePoder");
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		currentResult= service.agregarPoderSuperheroe(superheroeId, poder);
		assertThat(currentResult).isNotEmpty();
		
	}
	
	@Test
	void test_agregarPoderSuperheroeEmpty() throws Exception{
		List<Poder>poderes=new ArrayList<>();
		poderes.add(new Poder("Nombre"));
		Optional<Superheroe> expectedResult =Optional.empty();
		Optional<Superheroe> currentResult=Optional.empty();
		
		Poder poder=new Poder("NombrePoder");
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		try {
			currentResult= service.agregarPoderSuperheroe(superheroeId, poder);
		}
		catch(ResourceNotFoundException e) {
			assertThat(currentResult).isEmpty();
		}
		
		
		
	}
}
