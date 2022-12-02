package com.sysmap.miniautorizador.api.controller;

import com.sysmap.miniautorizador.api.dto.input.TransacaoInput;
import com.sysmap.miniautorizador.api.mapper.TransacaoDtoMapper;
import com.sysmap.miniautorizador.domain.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("transacoes")
public class TransacaoRestController {

    private final TransacaoService transacaoService;
    private final TransacaoDtoMapper transacaoDtoMapper;

    public TransacaoRestController(TransacaoDtoMapper transacaoDtoMapper,
                                   TransacaoService transacaoService){
       this.transacaoService=transacaoService;
       this.transacaoDtoMapper=transacaoDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String  criarTransacao(@Valid @RequestBody TransacaoInput transacaoInput ){
        transacaoService.autorizarTransacao(transacaoDtoMapper.toTransacao(transacaoInput));
        return "OK";
    }
}
