package com.sysmap.miniautorizador.domain.service;

import com.sysmap.miniautorizador.domain.exception.CartaoJaExistenteException;
import com.sysmap.miniautorizador.domain.exception.CartaoNaoExistenteException;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.repository.CartaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class CartaoService {

    private CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository repository){
        this.cartaoRepository=repository;
    }

    public Cartao criarCartao(Cartao cartao){

        cartaoRepository.findCartaoByNumeroCartao(cartao.getNumeroCartao())
                .ifPresent( (it)-> {
                    throw new CartaoJaExistenteException(
                    String.format(  "cartão com o número '%s' já existente",cartao.getNumeroCartao()),it
                    );
                });

        cartao.setSaldo(BigDecimal.valueOf(500));
          return this.cartaoRepository.save(cartao);
    }

    public Cartao actualizarCartao(Cartao cartao){
        return this.cartaoRepository.save(cartao);
    }

    public Cartao findCartaoByNumeroOrThrows(String numeroCartao){
        return cartaoRepository.findCartaoByNumeroCartao(numeroCartao)
                .orElseThrow(()-> new  CartaoNaoExistenteException(
                        String.format(  "cartão com o número '%s' não existe",numeroCartao)
                ));
    }

}
