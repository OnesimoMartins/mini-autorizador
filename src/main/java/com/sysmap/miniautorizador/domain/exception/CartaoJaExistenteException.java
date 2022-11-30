package com.sysmap.miniautorizador.domain.exception;

public class CartaoJaExistenteException extends RuntimeException {
    public CartaoJaExistenteException(String msg){
        super(msg);
    }
}
