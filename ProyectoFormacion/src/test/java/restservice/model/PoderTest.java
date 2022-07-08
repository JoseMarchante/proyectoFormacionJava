package restservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import restservice.dto.PoderDTO;

class PoderTest {
	@Test
	void beanDTO_null_test() {
		final PoderDTO checkNull=new PoderDTO("poder");
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
	}
	@Test
	void beanDTO_null_test2() {
		PoderDTO checkNull=new PoderDTO();
		checkNull.setNombre("PoderDTO");
		assertThat(checkNull.getNombre()).isNotEmpty();
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
		
	}
	@Test
	void bean_null_test() {
		final Poder checkNull=new Poder("poder");
		assertThat(checkNull).isNotNull();
		
		final Poder checkNull2=new Poder(1);
		assertThat(checkNull2).isNotNull();
		
	}
	@Test
	void bean_null_test2() {
		final Poder checkNull=new Poder();
		checkNull.setId(1);
		checkNull.setNombre("Poder");
		assertThat(checkNull).isNotNull();
		assertThat(checkNull.getId()).isNotNull();
		assertThat(checkNull.getNombre()).isNotNull();
	}
}
