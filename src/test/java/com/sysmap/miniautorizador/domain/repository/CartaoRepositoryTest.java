package com.sysmap.miniautorizador.domain.repository;

import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.utils.ListCartaoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartaoRepositoryTest {

    @Autowired
    private CartaoRepository cartaoRepository;

    @BeforeEach
    public void persistir_cartoes(){

        cartaoRepository.saveAll(ListCartaoFactory
                .getListaCartao(
                        ListCartaoFactory.TipoCartao.SEM_ID
                ));
    }

    @Test
    public void quando_persistir_cartao_deve_retornar_cartao_salvo(){
        Cartao cartao=new Cartao();
        cartao.setNumeroCartao("9376586897098000");
        cartao.setSenha("1234");
        cartao.setSaldo(BigDecimal.valueOf(500));

       Cartao cartaoSalvo= cartaoRepository.save(cartao);

        assertAll(
                ()->assertNotNull(cartaoSalvo.getId()),
                ()->assertNotNull(cartaoSalvo.getSenha())
        );
    }

    @Test
    public void quando_buscar_cartoes_retorna_todos_cartoes(){
        List<Cartao> cartoes=cartaoRepository.findAll();
        assertTrue(cartoes.size()>=3);
    }

    @Test
    public void quando_buscar_cartao_pelo_numero_retorna_cartao(){
        Cartao cartao=this.cartaoRepository.findCartaoByNumeroCartao("0376586897098000").get();

        assertAll(
                ()->assertNotNull(cartao),
                ()->assertEquals(cartao.getNumeroCartao(),"0376586897098000")
        );
    }

    @Test
    public void deve_lancar_NoSuchElementException_ao_buscar_cartao_pelo_numero(){

        assertThrows(NoSuchElementException.class,
                ()->this.cartaoRepository.findCartaoByNumeroCartao("0376586888098000").get()
                );
         }
}
