package restservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

public class SuperheroePoderTest {
	@Test
	void beanDTO_null_test() {
		final SuperheroePoder checkNull=new SuperheroePoder();
		if(StringUtils.isBlank(checkNull.toString())) {
			Assertions.fail();
		}
	}
	@Test
	void beanDTO_null_test2() {
		SuperheroePoder checkNull=new SuperheroePoder();
		SuperheroePoderKey key=new SuperheroePoderKey();
		key.setPoder_id(1);
		key.setSuperheroe_id(1);
		checkNull.setId(key);
		checkNull.setSuperheroe(new Superheroe("Nombre"));
		checkNull.setPoder(new Poder("Nombre"));
		
		assertThat(checkNull.getId()).isNotNull();
		assertThat(checkNull.getPoder()).isNotNull();
		assertThat(checkNull.getSuperheroe()).isNotNull();
		assertThat(key.getPoder_id()).isNotNull();
		assertThat(key.getSuperheroe_id()).isNotNull();
	}
}
