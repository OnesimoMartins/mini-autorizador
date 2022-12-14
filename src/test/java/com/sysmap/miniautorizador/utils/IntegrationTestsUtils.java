package com.sysmap.miniautorizador.utils;

import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.api.dto.input.TransacaoInput;
import com.sysmap.miniautorizador.api.dto.response.CartaoResponse;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.model.Transacao;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IntegrationTestsUtils {

    public static final String NUMERO_CARTAO = "6549873025634501";
    public static final String SENHA = "1234";

    public static Transacao TransacaoValida(){
        Transacao transacao=new Transacao();
        transacao.setValor(BigDecimal.valueOf(7));
        transacao.setCartao(CartaoSalvo());
        return transacao;
    }

    public static Transacao TransacaoCartaoInexistente(){
        Transacao transacao=new Transacao();
        transacao.setCartao(CartaoSemId());
        return transacao;
    }

    public static Cartao CartaoSemId() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");
        cartao.setNumeroCartao("9826354727658271");
        return cartao;
    }

    public static NovoCartaoInput NovoCartaoInput() {
        return new NovoCartaoInput(NUMERO_CARTAO, SENHA);
    }

    public static TransacaoInput TransacaoInput(){
        return new  TransacaoInput(NUMERO_CARTAO,SENHA,BigDecimal.valueOf(10));
    }

    public static ResultMatcher isCartaoValido() {

        return ResultMatcher.matchAll(
                jsonPath("$.numeroCartao").isString(),
                jsonPath("$.senha").isString()
        );
    }

    public static CartaoResponse CartaoResponse() {
        return new CartaoResponse("9826354727658271", "1234");
    }

    public static Cartao CartaoSalvo() {
        Cartao cartao = CartaoSemId();
        cartao.setId(4L);
        cartao.setSaldo(BigDecimal.TEN);
        return cartao;
    }

}
