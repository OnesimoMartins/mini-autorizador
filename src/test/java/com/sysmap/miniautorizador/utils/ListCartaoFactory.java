package com.sysmap.miniautorizador.utils;

import com.sysmap.miniautorizador.domain.model.Cartao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ListCartaoFactory {

    public enum TipoCartao{
        COM_ID,SEM_ID
    }

    public static List<Cartao> getListaCartao(TipoCartao tipoCartao){
        return TipoCartao.COM_ID.equals(tipoCartao) ? getListaCartaoComId()
                : getListaCartaoSemId();
    }

    private  static List<Cartao> getListaCartaoSemId(){

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

        return Arrays.asList(cartao1,cartao2,cartao3);
    }

    private static  List<Cartao> getListaCartaoComId(){

        Cartao cartao1= new Cartao();
        cartao1.setId(1L);
        cartao1.setNumeroCartao("0376586897098000");
        cartao1.setSenha("0234");
        cartao1.setSaldo(BigDecimal.valueOf(500));

        Cartao cartao2= new Cartao();
        cartao2.setId(2L);
        cartao2.setNumeroCartao("2376586897098000");
        cartao2.setSenha("1234");
        cartao2.setSaldo(BigDecimal.valueOf(500));

        Cartao cartao3= new Cartao();
        cartao3.setId(3L);
        cartao3.setNumeroCartao("3376586897098000");
        cartao3.setSenha("2234");
        cartao3.setSaldo(BigDecimal.valueOf(500));

        return Arrays.asList(cartao1,cartao2,cartao3);
    }
}
