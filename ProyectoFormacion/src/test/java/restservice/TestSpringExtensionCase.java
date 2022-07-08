package restservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import restservice.controller.PoderController;
import restservice.controller.SuperheroeController;
import restservice.controller.UniversoController;
import restservice.service.PoderService;
import restservice.service.SuperheroeService;
import restservice.service.UniversoService;

import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(SpringExtension.class)
class TestSpringExtensionCase {	
	
	@MockBean
	PoderService servicePoder;

	@MockBean
	UniversoService serviceUniverso;
	
	@MockBean
	SuperheroeService serviceSuperheroe;
	
	@MockBean
	PoderController poderController;
	
	@MockBean
	UniversoController universoController;
	
	@MockBean
	SuperheroeController superheroeController;
	
    @Test
    void test() {
        assertThat(servicePoder).isNotNull();
        assertThat(serviceUniverso).isNotNull();
        assertThat(serviceSuperheroe).isNotNull();
        
        assertThat(poderController).isNotNull();
        assertThat(universoController).isNotNull();
        assertThat(superheroeController).isNotNull();
    }

}
