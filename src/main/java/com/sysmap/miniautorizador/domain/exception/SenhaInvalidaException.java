package com.sysmap.miniautorizador.domain.exception;

public class SenhaInvalidaException extends RuntimeException{

    public SenhaInvalidaException(String msg){
        super(msg);
    }
}
