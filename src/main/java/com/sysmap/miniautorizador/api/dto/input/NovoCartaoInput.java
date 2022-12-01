package com.sysmap.miniautorizador.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NovoCartaoInput {

    /**
     * @numeroCartao regex que permite validar uma “string” (composta por números) de 16 dígitos
     * @senha regex que permite validar uma “string” (composta por números) de 4 dígitos.
     **/
    @Pattern(regexp = "^[0-9]{16}")
    @NotBlank
    private final String numeroCartao;

    @Pattern(regexp = "^[0-9]{4}")
    @NotBlank
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

    @Override
    public String toString() {
        return "NovoCartaoInput{" +
                "numeroCartao='" + numeroCartao + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
