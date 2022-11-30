package com.sysmap.miniautorizador.domain.service;

import com.sysmap.miniautorizador.domain.exception.CartaoJaExistenteException;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.repository.CartaoRepository;
import org.springframework.stereotype.Service;


@Service
public class CartaoService {

    private CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository repository){
        this.cartaoRepository=repository;
    }

    public Cartao salvarCartao(Cartao cartao){

        cartaoRepository.findCartaoByNumeroCartao(cartao.getNumeroCartao())
                .ifPresent( (i)-> {
                    throw new CartaoJaExistenteException(
                    String.format(  "cartão com o número '%s' já existente",cartao.getNumeroCartao())
                    );
                });

          return this.cartaoRepository.save(cartao);
    }

}
