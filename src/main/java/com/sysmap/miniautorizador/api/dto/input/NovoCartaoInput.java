package com.sysmap.miniautorizador.api.dto.input;

import javax.validation.constraints.Pattern;

public class NovoCartaoInput {


    private final String numeroCartao;
    private final String senha;

    public NovoCartaoInput(String numeroCartao, String senha) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
