package com.sysmap.miniautorizador.domain.exception;

public class SaldoInsuficienteException extends RuntimeException{

    public SaldoInsuficienteException(String msg){
        super(msg);
    }
}
