package com.sysmap.miniautorizador.api.dto.input;


import com.sysmap.miniautorizador.core.validation.NumeroCartaoValido;
import com.sysmap.miniautorizador.core.validation.SenhaCartaoValida;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransacaoInput {

    @NumeroCartaoValido
     private final String numeroCartao;

    @SenhaCartaoValida
     private final String senhaCartao;

     @NotNull
     @Positive
     private final BigDecimal valor;

    public TransacaoInput(String numeroCartao, String senhaCartao, BigDecimal valor) {
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    @Override
    public String toString() {
        return "TransacaoInput{" +
                "numeroCartao='" + numeroCartao + '\'' +
                ", senhaCartao='" + senhaCartao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
