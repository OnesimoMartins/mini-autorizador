package com.sysmap.miniautorizador.api.controller.exception;

import com.sysmap.miniautorizador.api.dto.response.CartaoResponse;
import com.sysmap.miniautorizador.api.dto.response.MensagemErro;
import com.sysmap.miniautorizador.api.mapper.CartaoDtoMapper;
import com.sysmap.miniautorizador.domain.exception.CartaoJaExistenteException;
import com.sysmap.miniautorizador.domain.exception.CartaoNaoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CartaoControllerExceptionHandler {

    @Autowired
    private  CartaoDtoMapper cartaoDtoMapper;

    @ExceptionHandler(CartaoJaExistenteException.class)
    public ResponseEntity<CartaoResponse> handleCartaoJaExistenteException
            (CartaoJaExistenteException ex){

        return ResponseEntity.status(422)
                .body(cartaoDtoMapper.toCartaoResponse(ex.getCartao()));
    }

    @ExceptionHandler(CartaoNaoExistenteException.class)
    public ResponseEntity<Object> handleCartaoJaExistenteException(
             HttpServletRequest request) {

       return request.getRequestURL().toString().endsWith("/transacoes") ?
               ResponseEntity.status(422).body(MensagemErro.CARTAO_INEXISTENTE.toString()) :
               ResponseEntity.notFound().build();

    }
}
