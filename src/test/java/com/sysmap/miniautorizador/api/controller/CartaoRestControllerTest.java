package com.sysmap.miniautorizador.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.api.mapper.CartaoDtoMapper;
import com.sysmap.miniautorizador.domain.service.CartaoService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.sysmap.miniautorizador.utils.IntegrationTestsUtils.*;

@WebMvcTest(CartaoRestController.class)
public class CartaoRestControllerTest {

    @MockBean
    private CartaoDtoMapper cartaoDtoMapper;
    @MockBean
    private CartaoService cartaoService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deve_retornar_201_ao_criar_cartao() throws Exception {

        when(cartaoService.criarCartao(CartaoSemId())).thenReturn(CartaoSalvo());
        when(cartaoDtoMapper.toCartaoResponse( CartaoSalvo()) ).thenReturn(CartaoResponse());
        when(cartaoDtoMapper.toCartao(NovoCartaoInput())).thenReturn(CartaoSemId());

             mvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsBytes( NovoCartaoInput()  )))
                .andExpect(status().is(201));

    }
    @Test
    public void deve_retornar_200_ao_consultar_saldo_criar_cartao() throws Exception {

        when(cartaoService.findCartaoByNumeroOrThrows(CartaoSalvo().getNumeroCartao()))
                .thenReturn(CartaoSalvo());

        when(cartaoDtoMapper.toCartao(NovoCartaoInput())).thenReturn(CartaoSemId());

        mvc.perform(get(String.format("/cartoes/%s",CartaoSalvo().getNumeroCartao()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().string("10"));
    }


    @MethodSource("getNovosCartoesInvalidos")
    @ParameterizedTest
    public void deve_falhar_ao_criar_novoCartao_dado_body_invalido(NovoCartaoInput novoCartaoInput) throws Exception {
        mvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(novoCartaoInput)))
                .andExpect(status().is(400));
    }

    private static Stream<Arguments> getNovosCartoesInvalidos(){
        return Stream.of(
                Arguments.of(new NovoCartaoInput("982635472765827a","1234")),
                Arguments.of(new NovoCartaoInput("9826354727658279","123")),
                Arguments.of(new NovoCartaoInput("98263547276582","1234")),
                Arguments.of(new NovoCartaoInput("9826354727658271",""))
        );
    }
}
