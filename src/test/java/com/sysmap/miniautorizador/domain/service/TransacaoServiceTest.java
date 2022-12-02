package com.sysmap.miniautorizador.domain.service;

import com.sysmap.miniautorizador.domain.exception.CartaoJaExistenteException;
import com.sysmap.miniautorizador.domain.exception.SaldoInsuficienteException;
import com.sysmap.miniautorizador.domain.exception.SenhaInvalidaException;
import com.sysmap.miniautorizador.domain.model.Transacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.sysmap.miniautorizador.utils.IntegrationTestsUtils.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private CartaoService cartaoService;

    @Test
    public void deve_realizar_transacao_com_sucesso(){

        when(cartaoService.findCartaoByNumeroOrThrows(TransacaoValida().getCartao().getNumeroCartao()))
             .thenReturn(TransacaoValida().getCartao());

     BigDecimal valorRetirado=TransacaoValida().getValor();
     BigDecimal saldoAntesDebito=TransacaoValida().getCartao().getSaldo();
     BigDecimal saldoAposDebito= transacaoService.autorizarTransacao(TransacaoValida()).getCartao().getSaldo();

     assertEquals(valorRetirado.add(saldoAposDebito),saldoAntesDebito);

    }

    @Test
    public void deve_lancar_CartaoJaExistenteException_ao_relizar_transacao_com_cartao_inexistente(){
        when(cartaoService.findCartaoByNumeroOrThrows(TransacaoCartaoInexistente().getCartao().getNumeroCartao()))
                .thenThrow(CartaoJaExistenteException.class);

        assertThrows(CartaoJaExistenteException.class,()->{
            transacaoService.autorizarTransacao(TransacaoCartaoInexistente());
        });
    }

    @Test
    public void deve_falhar_realizar_transacao_com_senha_errada(){
        Transacao tentativaTransacao=TransacaoValida();
        tentativaTransacao.getCartao().setSenha("2222");

        when(cartaoService.findCartaoByNumeroOrThrows(TransacaoValida().getCartao().getNumeroCartao()))
                .thenReturn(TransacaoValida().getCartao());

        assertThrows(SenhaInvalidaException.class,
                ()->{
            transacaoService.autorizarTransacao(tentativaTransacao);
        });
    }


    @Test
    public void deve_falhar_reliza_transacao_com_saldo_insufuciente(){
        Transacao tentativaTransacao=TransacaoValida();
        tentativaTransacao.setValor(BigDecimal.valueOf(100));

        when(cartaoService.findCartaoByNumeroOrThrows(TransacaoValida().getCartao().getNumeroCartao()))
                .thenReturn(TransacaoValida().getCartao());

        assertThrows(SaldoInsuficienteException.class,
                ()->{
                    transacaoService.autorizarTransacao(tentativaTransacao);
                });
    }
}
