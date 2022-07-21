package br.com.rodrigo.onepiece;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.rodrigo.onepiece.dto.FrutaDTO;
import br.com.rodrigo.onepiece.service.FrutaService;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@WebAppConfiguration
class OnepieceApplicationTests {

	@Autowired
	private FrutaService frutaService;

	@Test
	void buscarPorIdTest() {
		FrutaDTO fruta = frutaService.findId(1L);
		assertEquals("1", String.valueOf(fruta.getId()));
		assertEquals("2", String.valueOf(fruta.getCategoria().getId()));
		assertEquals("Gomu Gomu no Mi (Fruta da Borracha)", fruta.getNome());
	}

}
