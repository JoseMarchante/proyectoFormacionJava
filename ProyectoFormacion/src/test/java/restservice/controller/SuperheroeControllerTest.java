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
import org.springframework.web.bind.annotation.GetMapping;

import restservice.dto.PoderDTO;
import restservice.dto.SuperheroeDTO;
import restservice.exception.ResourceNotFoundException;
import restservice.model.Poder;
import restservice.model.Superheroe;
import restservice.model.Universo;
import restservice.service.SuperheroeService;
import restservice.service.UniversoService;

@ExtendWith(MockitoExtension.class)
class SuperheroeControllerTest {
	

	@InjectMocks
	SuperheroeController controller;
	
	@Mock
	SuperheroeService service;
	
	private Integer superheroeId;
	private Integer poderId;
	@BeforeEach // JUNIT 5
	void setup_test() {
		
		// INIT TEST VARIABLES	
		superheroeId = Integer.valueOf(1);
		poderId= Integer.valueOf(1);
	}
	
	@Test
	void test_superheroes() throws Exception{
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Superheroe> expectedResult=new ArrayList<>();
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		expectedResult.add(new Superheroe("Batman",true,new Universo("Marvel"),poderes));
		expectedResult.add(new Superheroe("Spider",true,new Universo("DC"),poderes));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllSuperheroes()).thenReturn(expectedResult);
		
		// LLAMADA A MÉTODO A TESTEAR
		List<String> currentResult=controller.superheroes();
		
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotNull();
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_superheroes_KO() throws Exception {
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<String> currentResult=new ArrayList<>();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllSuperheroes()).thenThrow(new ResourceNotFoundException("No existen superheroes en la base de datos!"));		
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
			currentResult= controller.superheroes();
			
			
		} catch (ResourceNotFoundException e) {
			assertThat(currentResult).isEmpty();
		}
	}
	@Test
	void test_superheroes_empty() throws Exception {
		
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Superheroe> expectedResult=new ArrayList<>();
		List<String> currentResult=new ArrayList<>();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findAllSuperheroes()).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existen superheroes en la base de datos!"));
		
		// LLAMADA A MÉTODO A TESTEAR
		try {
		currentResult = controller.superheroes();
		}
		catch(ResourceNotFoundException w) {
			assertThat(expectedResult).isEmpty();
		}
		
	}
	
	@Test
	void test_superheroe_id() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Optional<Superheroe> expectedResult=Optional.of(new Superheroe("Nombre",true,new Universo("Nombre"),poderes));
		String currentResult="";
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.getSuperheroeById(superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_superheroe_idNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Superheroe> expectedResult=Optional.empty();
		String currentResult;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existe el superheroe en la base de datos!"));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.getSuperheroeById(superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		
	}
	
	@Test
	void test_superheroe_ByName() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Superheroe superheroe=new Superheroe("Nombre",true,new Universo("Nombre"),poderes);
		List<String> currentResult=new ArrayList<>();
		
		List<Superheroe> expectedResult =new ArrayList<>();
		expectedResult.add(superheroe);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findByNameContaining("mbr")).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult = controller.superheroesByName("mbr");
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isNotEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotEmpty();
	}
	
	@Test
	void test_superheroe_morir() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		
		String currentResult;
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Superheroe superheroe=new Superheroe("Nombre",true,new Universo("Nombre"),poderes);
		Optional<Superheroe> expectedResult=Optional.of(superheroe);
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.matarSuperheroe(superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		
	}
	
	@Test
	void test_superheroe_revivir() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		
		String currentResult;
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Superheroe superheroe=new Superheroe("Nombre",false,new Universo("Nombre"),poderes);
		Optional<Superheroe> expectedResult=Optional.of(superheroe);
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.revivirSuperheroe(superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		
	}
	
	@Test
	void test_superheroe_morirNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		
		String currentResult;
		Optional<Superheroe> expectedResult=Optional.empty();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existe el superheroe en la base de datos!"));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.matarSuperheroe(superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		
	}
	
	@Test
	void test_superheroe_revivirNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		
		Optional<Superheroe> expectedResult=Optional.empty();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existe el superheroe en la base de datos!"));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.revivirSuperheroe(superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		
	}
	@Test
	void test_create_superheroe() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		Superheroe expectedResult=new Superheroe("Nombre",false,new Universo("Nombre"),poderes);
		SuperheroeDTO currentResult=new SuperheroeDTO(new Superheroe("Nombre",false,new Universo("Nombre"),poderes));
		
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		controller.crearSuperheroe(currentResult);
		service.guardarSuperheroe(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		assertThat(currentResult).isNotNull();
		assertThat(expectedResult).isNotNull();
	}
	@Test
	void test_superheroe_update() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		
		List<Integer> poder=new ArrayList<>();
		poder.add(poderId);
		
		Optional<Superheroe> expectedResult=Optional.of(new Superheroe("Nombre",true,new Universo("Nombre"),poderes));
		SuperheroeDTO superheroeDTO=new SuperheroeDTO("Nombre",true,1, poder);
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.updateSuperheroe(superheroeDTO,superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(expectedResult).isNotEmpty();
	}
	@Test
	void test_superheroe_updateNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		
		List<Integer> poder=new ArrayList<>();
		poder.add(poderId);
		
		Optional<Superheroe> expectedResult=Optional.empty();
		SuperheroeDTO superheroeDTO=new SuperheroeDTO("Nombre",true,1, poder);
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existe el superheroe en la base de datos!"));
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.updateSuperheroe(superheroeDTO,superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(expectedResult).isEmpty();
	}
	@Test
	void test_superheroe_agregarPoder() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		
		List<Integer> poder=new ArrayList<>();
		poder.add(poderId);
		
		Optional<Superheroe> expectedResult=Optional.of(new Superheroe("Nombre",true,new Universo("Nombre"),poderes));
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.agregarPoderSuperheroe(poderId, superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(expectedResult).isNotEmpty();
	}
	@Test
	void test_superheroe_agregarPoderNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		
		List<Integer> poder=new ArrayList<>();
		poder.add(poderId);
		
		Optional<Superheroe> expectedResult=Optional.empty();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existe el superheroe en la base de datos!"));;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.agregarPoderSuperheroe(poderId, superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(expectedResult).isEmpty();
	}
	
	@Test
	void test_superheroe_eliminarPoder() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		
		List<Integer> poder=new ArrayList<>();
		poder.add(poderId);
		
		Optional<Superheroe> expectedResult=Optional.of(new Superheroe("Nombre",true,new Universo("Nombre"),poderes));
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.eliminarPoderSuperheroe(poderId, superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(expectedResult).isNotEmpty();
	}
	
	@Test
	void test_superheroe_eliminarPoderNull() throws Exception{
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("podercito"));
		poderes.add(new Poder("podercito2"));
		
		List<Integer> poder=new ArrayList<>();
		poder.add(poderId);
		
		Optional<Superheroe> expectedResult=Optional.empty();
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult).thenThrow(new ResourceNotFoundException("No existe el superheroe en la base de datos!"));;
		
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			controller.eliminarPoderSuperheroe(poderId, superheroeId);
			}
			catch(ResourceNotFoundException w) {
				assertThat(expectedResult).isEmpty();
			}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(expectedResult).isEmpty();
	}
	
	@Test
	void test_delete_superheroe_id() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Superheroe> expectedResult = Optional.of(new Superheroe("Nombre"));
		ResponseEntity<SuperheroeDTO> currentResult = null;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult=controller.deleteSuperheroeById(superheroeId);
		} catch (ResourceNotFoundException w) {
			assertThat(currentResult).isNull();
		}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNotNull();
	}
	@Test
	void test_delete_superheroe_idNull() throws Exception {
		// DEFINICIÓN DE VARIABLES DE ENTRADA Y RESULTADOS
		Optional<Superheroe> expectedResult = Optional.empty();
		ResponseEntity<SuperheroeDTO> currentResult = null;
		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		when(service.findSuperheroe(superheroeId)).thenReturn(expectedResult);

		// COMPORTAMIENTO ESPERADO DEL CUERPO DEL MÉTODO
		try {
			currentResult=controller.deleteSuperheroeById(superheroeId);
		} catch (ResourceNotFoundException w) {
			assertThat(currentResult).isNull();
		}
		// COMPROBACIONES DEL RESULTADO ESPERADO;
		assertThat(currentResult).isNull();
	}
}
