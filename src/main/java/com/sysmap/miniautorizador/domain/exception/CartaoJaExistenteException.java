package com.sysmap.miniautorizador.domain.exception;

import com.sysmap.miniautorizador.domain.model.Cartao;

public class CartaoJaExistenteException extends RuntimeException {

    private final Cartao cartao;
    public CartaoJaExistenteException(String msg,Cartao cartao){
        super(msg);
        this.cartao=cartao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
