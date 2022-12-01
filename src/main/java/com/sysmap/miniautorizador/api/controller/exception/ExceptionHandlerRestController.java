package com.sysmap.miniautorizador.api.controller.exception;

import com.sysmap.miniautorizador.api.dto.response.CartaoResponse;
import com.sysmap.miniautorizador.api.mapper.CartaoDtoMapper;
import com.sysmap.miniautorizador.domain.exception.CartaoJaExistenteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerRestController {

    private final CartaoDtoMapper cartaoDtoMapper;

    public ExceptionHandlerRestController(CartaoDtoMapper cartaoDtoMapper){
       this.cartaoDtoMapper=cartaoDtoMapper;
    }

    @ExceptionHandler(CartaoJaExistenteException.class)
    public ResponseEntity<CartaoResponse> handleCartaoJaExistenteException(
            CartaoJaExistenteException ex){

        return ResponseEntity.status(422).body(
                cartaoDtoMapper.toCartaoResponse(ex.getCartao())
        ) ;
    }
}
