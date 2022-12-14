package com.sysmap.miniautorizador.domain.service;

import com.sysmap.miniautorizador.domain.exception.CartaoJaExistenteException;
import com.sysmap.miniautorizador.domain.exception.CartaoNaoExistenteException;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;
    @InjectMocks
    private CartaoService cartaoService;

    @Test
    public void deve_retornar_cartao_salvo_quando_salvar_cartao(){

        final Cartao novoCartao =new Cartao();
        novoCartao.setNumeroCartao("0987635467816253");
        novoCartao.setSenha("1456");

        Cartao cartaoSalvo=new Cartao();

        cartaoSalvo.setId(4L);
        cartaoSalvo.setSaldo(BigDecimal.valueOf(500));
        cartaoSalvo.setNumeroCartao(novoCartao.getNumeroCartao());
        cartaoSalvo.setSenha(novoCartao.getSenha());

        when(cartaoRepository.findCartaoByNumeroCartao("0987635467816253"))
                .thenReturn(Optional.empty());

        when(cartaoRepository.save(novoCartao)).thenReturn(cartaoSalvo);

         final Cartao resultCartaoSalvo=cartaoService.criarCartao(novoCartao);

        assertAll(
                ()->assertNotNull(resultCartaoSalvo),
                ()->assertEquals(resultCartaoSalvo.getId(),cartaoSalvo.getId()),
                ()->assertEquals(resultCartaoSalvo.getSaldo(), cartaoSalvo.getSaldo())
        );

    }

    @Test()
    public void deve_lancar_CartaoJaExistenteException_quando_salvar_cartao(){

        final Cartao novoCartao =new Cartao();
        novoCartao.setNumeroCartao("0987635467816253");
        novoCartao.setSenha("1456");

        final Cartao cartaoSalvo=new Cartao();
        cartaoSalvo.setNumeroCartao("0987635467816253");
        cartaoSalvo.setSenha("2456");

        when(cartaoRepository.findCartaoByNumeroCartao("0987635467816253"))
                .thenReturn(Optional.of(cartaoSalvo));

        assertThrows(CartaoJaExistenteException.class,
                ()->cartaoService.criarCartao(novoCartao)
        );

    }

    @Test
    public void deve_retornar_Cartao_quando_buscar_por_numero(){

        final Cartao cartao =CartaSalvo();
        String numeroCartao=cartao.getNumeroCartao();


        when(cartaoRepository.findCartaoByNumeroCartao(numeroCartao))
                .thenReturn(Optional.of( CartaSalvo() ));

        Cartao resultado =cartaoService.findCartaoByNumeroOrThrows(numeroCartao);

                assertEquals(resultado.getNumeroCartao(),numeroCartao);

    }
    @Test
    public void deve_lancar_CartaoNaoExistenteException_quando_buscar_cartao(){
        String numeroCartao= "0000000000000000";

        when(cartaoRepository.findCartaoByNumeroCartao(numeroCartao))
                .thenReturn(Optional.empty());

        assertThrows(CartaoNaoExistenteException.class,
                ()->cartaoService.findCartaoByNumeroOrThrows(numeroCartao));
    }

    private  Cartao CartaSalvo(){
        Cartao cartao= new Cartao();
        cartao.setNumeroCartao("0987635467816253");
        cartao.setSenha("2456");
        return cartao;
    }

}
