package com.sysmap.miniautorizador.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.api.mapper.CartaoDtoMapper;
import com.sysmap.miniautorizador.api.mapper.TransacaoDtoMapper;
import com.sysmap.miniautorizador.domain.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static com.sysmap.miniautorizador.utils.IntegrationTestsUtils.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoRestController.class)
public class TransacaoRestControllerTest {

    @MockBean
    private TransacaoDtoMapper transacaoDtoMapper;
    @MockBean
    private TransacaoService transacaoService;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CartaoDtoMapper cartaoDtoMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deve_retornar_201_ao_criar_transacao() throws Exception {

        when(cartaoDtoMapper.toCartao(new NovoCartaoInput(
                TransacaoValida().getCartao().getNumeroCartao(),
                TransacaoValida().getCartao().getSenha()

        ))).thenReturn(TransacaoValida().getCartao());

        when(transacaoDtoMapper.toTransacao(TransacaoInput())).thenReturn(TransacaoValida());

        when(transacaoService.autorizarTransacao(TransacaoValida())).thenReturn(TransacaoValida());

             mvc.perform(post("/transacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsBytes( TransacaoInput()  )))
                .andExpect(status().is(201));

    }

}
