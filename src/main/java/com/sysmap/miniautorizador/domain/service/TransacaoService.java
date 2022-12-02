package com.sysmap.miniautorizador.domain.service;

import com.sysmap.miniautorizador.domain.exception.SaldoInsuficienteException;
import com.sysmap.miniautorizador.domain.exception.SenhaInvalidaException;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.model.Transacao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Function;
@Service
public class TransacaoService {

    private final CartaoService cartaoService;

    public TransacaoService(CartaoService cartaoService){
        this.cartaoService=cartaoService;
    }

    @Transactional
    public Transacao autorizarTransacao(Transacao transacao){

         final String senhaTentativa=transacao.getCartao().getSenha();

        Cartao cartao= cartaoService
                .findCartaoByNumeroOrThrows(transacao.getCartao().getNumeroCartao());

        transacao.setCartao(cartao);

        this.validarSenhas.apply(new String[]{cartao.getSenha(),senhaTentativa});

        this.validarSaldo.apply(transacao);

        cartao.debitar(transacao.getValor());

        cartaoService.actualizarCartao(cartao);

        return transacao;
    }

    /*
    * a senha na posição 0 corresponde a senha do cartão e o na posição 1 é a
    * senha da requisição
    * */
   private final Function<String[],Boolean> validarSenhas=(senhas)->
       senhas[0].equals(senhas[1]) || throwException(new SenhaInvalidaException("Senha inválida"));;

    /*
    * o saldo na posição 0 corresponde o saldo no cartao o na posição 1 é o saldo a ser
    * descontado
    * */
   private Function<Transacao,Boolean> validarSaldo=(transacao)->
            transacao.isTransacaoValida() || throwException(new SaldoInsuficienteException("saldo insuficente"));

    private Boolean throwException(RuntimeException e){throw e;}

}
