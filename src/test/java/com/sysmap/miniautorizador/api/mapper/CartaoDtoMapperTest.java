package com.sysmap.miniautorizador.api.mapper;

import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.domain.model.Cartao;
import org.junit.jupiter.api.Test;

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
                ()->assertEquals(cartao.getSaldo(),null)
        );

    }
}
