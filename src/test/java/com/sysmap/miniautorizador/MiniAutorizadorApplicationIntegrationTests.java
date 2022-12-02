package com.sysmap.miniautorizador;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.sysmap.miniautorizador.utils.IntegrationTestsUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@Testcontainers
@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MiniAutorizadorApplicationIntegrationTests {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mvc;

	@Container
	public static MySQLContainer<?> mysql=new MySQLContainer<>("mysql:5.7")
			.withDatabaseName("miniautorizador_test")
			.withUsername("root")
			.withPassword("password");

	@DynamicPropertySource
	public static void withProperties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url",mysql::getJdbcUrl);
		registry.add("spring.datasource.username",mysql::getUsername);
		registry.add("spring.datasource.password",mysql::getPassword);
		registry.add("spring.jpa.show-sql",()->"true");
	}



	@Test
	@Order(1)
	@DisplayName("criação de um cartão")
	public void criar_cartao() throws Exception {

		mvc.perform(post("/cartoes")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes( NovoCartaoInput()  )))
				.andExpect(status().is(201))
				.andExpect(isCartaoValido());
	}

	@Test
	@Order(2)
	@DisplayName("verificação do saldo do cartão recém-criado")
	public void consultar_saldo_cartao() throws Exception {
		mvc.perform(get(String.format("/cartoes/%s",NUMERO_CARTAO))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("500.00"));
	}

}
