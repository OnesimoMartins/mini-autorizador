package com.sysmap.miniautorizador.api.controller;

import com.sysmap.miniautorizador.api.dto.input.NovoCartaoInput;
import com.sysmap.miniautorizador.api.dto.response.CartaoResponse;
import com.sysmap.miniautorizador.api.mapper.CartaoDtoMapper;
import com.sysmap.miniautorizador.domain.model.Cartao;
import com.sysmap.miniautorizador.domain.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("cartoes")
public class CartaoRestController {

    private final CartaoService cartaoService;
    private final CartaoDtoMapper cartaoDtoMapper;

    public CartaoRestController(CartaoService cartaoService, CartaoDtoMapper cartaoDtoMapper){
        this.cartaoService=cartaoService;
        this.cartaoDtoMapper=cartaoDtoMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoResponse criarNovoCartao(@Valid @RequestBody NovoCartaoInput novoCartaoInput){

     Cartao cartaoSalvo= cartaoService.criarCartao(
               cartaoDtoMapper.toCartao(novoCartaoInput)
       );

        return cartaoDtoMapper.toCartaoResponse(cartaoSalvo);
    }

    @GetMapping(value = "/{numeroCartao}")
    public BigDecimal obterSaldo(@PathVariable String numeroCartao){
        return  this.cartaoService.findCartaoByNumeroOrThrows(numeroCartao).getSaldo();
    }

}
