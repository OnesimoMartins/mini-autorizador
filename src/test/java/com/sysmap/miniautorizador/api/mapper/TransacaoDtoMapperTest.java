package com.sysmap.miniautorizador.api.mapper;

import com.sysmap.miniautorizador.api.dto.input.TransacaoInput;
import com.sysmap.miniautorizador.domain.model.Transacao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoDtoMapperTest {

    private TransacaoDtoMapper transacaoDtoMapper=new TransacaoDtoMapper(new CartaoDtoMapper());

    @Test
    public void deve_converter_TransacaoInput_para_Transacao(){

        TransacaoInput transacaoInput=new TransacaoInput("6549873025634501","1234"
                ,BigDecimal.valueOf(100));

        Transacao transacao= transacaoDtoMapper.toTransacao(transacaoInput);

        assertAll(
                ()->assertEquals(transacao.getCartao().getNumeroCartao(),transacaoInput.getNumeroCartao()),
                ()->assertEquals(transacao.getValor(),transacaoInput.getValor()),
                ()->assertEquals(transacao.getCartao().getSenha(),transacaoInput.getSenhaCartao()),
                ()->assertNull(transacao.getCartao().getId())
                );
    }


}
