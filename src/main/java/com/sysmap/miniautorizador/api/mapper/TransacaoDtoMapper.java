package com.sysmap.miniautorizador.api.mapper;

import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.api.dto.input.TransacaoInput;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransacaoDtoMapper {

    @Autowired
    private CartaoDtoMapper cartaoDtoMapper;

     public TransacaoDtoMapper(CartaoDtoMapper cartaoDtoMapper){
        this.cartaoDtoMapper=cartaoDtoMapper;
    }

    public Transacao toTransacao(TransacaoInput transacaoInput){

        Transacao transacao=new Transacao();
        transacao.setValor(transacaoInput.getValor());

        Cartao cartao=cartaoDtoMapper.toCartao(new NovoCartaoInput(
                transacaoInput.getNumeroCartao(),
                transacaoInput.getSenhaCartao()
                ));

                transacao.setCartao(cartao);

        return transacao;
    }

}
