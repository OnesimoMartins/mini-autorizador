package com.sysmap.miniautorizador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.miniautorizador.api.dto.input.TransacaoInput;
import com.sysmap.miniautorizador.api.dto.response.MensagemErro;
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

import java.math.BigDecimal;

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
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7")
			.withDatabaseName("miniautorizador_test")
			.withUsername("root")
			.withPassword("password");

	@DynamicPropertySource
	public static void withProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
		registry.add("spring.jpa.show-sql", () -> "true");
	}


	@Test
	@Order(1)
	@DisplayName("criação de um cartão")
	public void criar_cartao() throws Exception {

		mvc.perform(post("/cartoes")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(NovoCartaoInput())))
				.andExpect(status().is(201))
				.andExpect(isCartaoValido());
	}

	@Test
	@Order(2)
	@DisplayName("verificação do saldo do cartão recém-criado")
	public void consultar_saldo_cartao() throws Exception {
		mvc.perform(get(String.format("/cartoes/%s", NUMERO_CARTAO))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("500.00"));
	}

	@Test
	@Order(3)
	@DisplayName("Diversas transações válidas")
	public void varias_transacoes_validas() throws Exception {

		for (int i = 1; i <= 2; i++) {
			TransacaoInput transacaoInput = new TransacaoInput(NUMERO_CARTAO, SENHA,
					BigDecimal.valueOf(i * 120));

			mvc.perform(post("/transacoes")
							.accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsBytes(transacaoInput))
					)
					.andExpect(status().isCreated());
		}
	}

	@Test
	@Order(4)
	@DisplayName("obter saldo insuficiente como resposta")
	public void transacoes_ate_saldo_insuficiente() throws Exception {
		TransacaoInput transacaoInput = new TransacaoInput(NUMERO_CARTAO, SENHA, BigDecimal.valueOf(300));
		mvc.perform(post("/transacoes")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(transacaoInput))
				)
				.andExpect(status().is(422))
				.andExpect(content().string(MensagemErro.SALDO_INSUFICIENTE.toString()));
	}


	@Test
	@Order(5)
	@DisplayName("realização de uma transação com senha inválida")
	public void transacao_senha_invalida() throws Exception {
		TransacaoInput transacaoInput = new TransacaoInput(NUMERO_CARTAO
				, "0000", BigDecimal.valueOf(10));

		mvc.perform(post("/transacoes")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(transacaoInput))
				)
				.andExpect(status().is(422))
				.andExpect(content().string(MensagemErro.SENHA_INVALIDA.toString()));
	}

	@Test
	@Order(6)
	@DisplayName("realização de uma transação com cartão inexistente")
	public void transacao_cartao_inexistente() throws Exception {
		TransacaoInput transacaoInput = new TransacaoInput("0000000000000000"
				, SENHA, BigDecimal.valueOf(10));

		mvc.perform(post("/transacoes")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(transacaoInput)))

				.andExpect(status().is(422))
				.andExpect(content().string(MensagemErro.CARTAO_INEXISTENTE.toString()));
	}


}
