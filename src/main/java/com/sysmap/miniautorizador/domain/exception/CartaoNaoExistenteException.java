package com.sysmap.miniautorizador.domain.exception;

public class CartaoNaoExistenteException extends RuntimeException {
    public CartaoNaoExistenteException(String msg) {
        super(msg);
    }
}
