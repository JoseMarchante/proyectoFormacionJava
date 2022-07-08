package restservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import restservice.dto.SuperheroeDTO;

class SuperheroeTest {
	
	@Test
	void beanDTO_null_test() {
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("Poder"));
		final SuperheroeDTO checkNull=new SuperheroeDTO(new Superheroe("Nombre",false,new Universo("Universo"),poderes));
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
	}
	@Test
	void beanDTO_test() {
		SuperheroeDTO checkNull=new SuperheroeDTO();
		List<Integer> poderes=new ArrayList<>();
		poderes.add(1);
		
		
		checkNull.setNombre("nombre");
		checkNull.setEstado(false);
		checkNull.setUniversoId(1);
		checkNull.setPoderes(poderes);
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
	}
	@Test
	void bean_null_test() {
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("Podercito"));
		Superheroe checkNull=new Superheroe("Nombre",true,new Universo("universo"),poderes);
		checkNull.setId(1);
		checkNull.setId_universo(1);
		assertThat(checkNull.getId()).isNotNull();
		assertThat(checkNull.getNombre()).isNotNull();
		assertThat(checkNull.getEstado()).isNotNull();
		assertThat(checkNull.getUniverso()).isNotNull();
		assertThat(checkNull.getPoder()).isNotEmpty();
		assertThat(checkNull.getId_universo()).isNotNull();
	}
	@Test
	void bean_test() {
		Superheroe checkNull=new Superheroe();
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("Podercito"));
		
		checkNull.setNombre("nombre");
		checkNull.setEstado(false);
		checkNull.setUniverso(new Universo("Universo"));
		checkNull.setPoder(poderes);
		
	}
	@Test
	void bean_test2() {
		Superheroe checkNull=new Superheroe("Nombre");
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("Podercito"));
	
		checkNull.setEstado(false);
		checkNull.setUniverso(new Universo("Universo"));
		checkNull.setPoder(poderes);
		
		assertThat(checkNull.getNombre()).isNotEmpty();
		assertThat(checkNull.getEstado()).isNotNull();
		assertThat(checkNull.getUniverso()).isNotNull();
		assertThat(checkNull.getPoder()).isNotEmpty();
	
	}
	@Test
	void bean_testWithId() {
		Superheroe checkNull=new Superheroe();
		List<Poder> poderes=new ArrayList<>();
		poderes.add(new Poder("Podercito"));
		
		checkNull.setNombre("nombre");
		checkNull.setEstado(false);
		checkNull.setId_universo(1);
		checkNull.setPoder(poderes);
		
	}
}
