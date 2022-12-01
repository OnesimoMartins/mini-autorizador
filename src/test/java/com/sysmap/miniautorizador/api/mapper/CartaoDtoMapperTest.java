package com.sysmap.miniautorizador.api.mapper;

import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.api.dto.response.CartaoResponse;
import com.sysmap.miniautorizador.domain.model.Cartao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoDtoMapperTest {

    private CartaoDtoMapper cartaoDtoMapper=new CartaoDtoMapper();

    @Test
    public void deve_converter_cartaoInput_para_cartao(){
        NovoCartaoInput cartaoInput=
                new NovoCartaoInput("0987635467816253","1235");

        Cartao cartao= cartaoDtoMapper.toCartao(cartaoInput);

        assertAll(
                ()->assertEquals(cartao.getNumeroCartao(),cartaoInput.getNumeroCartao()),
                ()->assertEquals(cartao.getSenha(),cartaoInput.getSenha()),
                ()->assertNull(cartao.getSaldo())
        );
    }
    @Test
    public void deve_converter_cartao_para_cartaoResponse(){
        Cartao cartao=new Cartao();
        cartao.setSaldo(BigDecimal.valueOf(300));
        cartao.setNumeroCartao("0987635467816253");
        cartao.setSenha("1234");

        CartaoResponse cartaoResponse= cartaoDtoMapper.toCartaoResponse(cartao);
        assertAll(
                ()->assertEquals(cartaoResponse.getNumeroCartao(),cartao.getNumeroCartao()),
                ()->assertEquals(cartaoResponse.getSenha(),cartao.getSenha())
        );

    }


}
