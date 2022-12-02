package com.sysmap.miniautorizador.api.controller.exception;

import com.sysmap.miniautorizador.api.dto.response.MensagemErro;
import com.sysmap.miniautorizador.domain.exception.SaldoInsuficienteException;
import com.sysmap.miniautorizador.domain.exception.SenhaInvalidaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransacaoControllerExceptionHandler {


    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleCartaoJaExistenteException(){
        return ResponseEntity.status(422).body(MensagemErro.SALDO_INSUFICIENTE.toString());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<String> handleSenhaInvalidaException(){
        return ResponseEntity.status(422).body(MensagemErro.SENHA_INVALIDA.toString());
    }

}
