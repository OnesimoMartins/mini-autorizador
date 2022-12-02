package com.sysmap.miniautorizador.domain.model;

import com.sysmap.miniautorizador.domain.exception.SaldoInsuficienteException;

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

    /*
    * retorna true caso for valida e false caso inválida
    * */
    public boolean isTransacaoValida(){
       return this.getCartao().getSaldo().subtract(this.valor).compareTo(BigDecimal.ZERO)>-1;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "cartao=" + cartao +
                ", valor=" + valor +
                '}';
    }
}
