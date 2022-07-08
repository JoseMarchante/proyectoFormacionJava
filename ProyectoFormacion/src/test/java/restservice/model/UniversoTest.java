package restservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import restservice.dto.UniversoDTO;

class UniversoTest {
	@Test
	void beanDTO_null_test() {
		final UniversoDTO checkNull=new UniversoDTO("Universo");
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
	}
	@Test
	void beanDTO_null_test2() {
		UniversoDTO checkNull=new UniversoDTO();
		checkNull.setNombre("UniversoDTO");
		assertThat(checkNull.getNombre()).isNotEmpty();
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
		
	}
	@Test
	void bean_null_test() {
		final Universo checkNull=new Universo("Universo");
		assertThat(checkNull).isNotNull();
	}
	@Test
	void bean_null_test2() {
		final Universo checkNull=new Universo();
		checkNull.setId(1);
		checkNull.setNombre("Universo");
		assertThat(checkNull).isNotNull();
		assertThat(checkNull.getId()).isNotNull();
		assertThat(checkNull.getNombre()).isNotNull();
	}
}
