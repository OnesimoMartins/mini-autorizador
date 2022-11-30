package com.sysmap.miniautorizador.api.mapper;

import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.domain.model.Cartao;
import org.springframework.stereotype.Component;

@Component
public class CartaoDtoMapper {

    public Cartao toCartao(NovoCartaoInput novoCartaoInput){
        Cartao cartao=new Cartao();

        cartao.setSenha(cartao.getSenha());
        cartao.setNumeroCartao(cartao.getNumeroCartao());
        cartao.setSaldo(null);

        return cartao;
    }
}
