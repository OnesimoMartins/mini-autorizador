package com.sysmap.miniautorizador.domain.repository;

import com.sysmap.miniautorizador.domain.model.Cartao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartaoRepositoryTest {

    @Autowired
    private CartaoRepository cartaoRepository;

    @BeforeEach
    public void persistir_cartoes(){
        Cartao cartao1= new Cartao();
        cartao1.setNumeroCartao("0376586897098000");
        cartao1.setSenha("0234");
        cartao1.setSaldo(BigDecimal.valueOf(500));

        Cartao cartao2= new Cartao();
        cartao2.setNumeroCartao("2376586897098000");
        cartao2.setSenha("1234");
        cartao2.setSaldo(BigDecimal.valueOf(500));

        Cartao cartao3= new Cartao();
        cartao3.setNumeroCartao("3376586897098000");
        cartao3.setSenha("2234");
        cartao3.setSaldo(BigDecimal.valueOf(500));

        cartaoRepository.saveAll(Arrays.asList(cartao1,cartao2,cartao3));
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
        assertEquals(cartoes.size(),3);
    }
}
