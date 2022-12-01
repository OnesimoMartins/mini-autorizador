package com.sysmap.miniautorizador.api.dto.response;


public class CartaoResponse {
    
    private final String numeroCartao;
    private final String senha;

    public CartaoResponse(String numeroCartao,String senha){
        this.numeroCartao=numeroCartao;
        this.senha=senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "CartaoResponse{" +
                "numeroCartao='" + numeroCartao + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
