package com.sysmap.miniautorizador.domain.model;

import java.math.BigDecimal;

public class Transacao {

    private Cartao cartao;
    private BigDecimal valor;


    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "cartao=" + cartao +
                ", valor=" + valor +
                '}';
    }
}
