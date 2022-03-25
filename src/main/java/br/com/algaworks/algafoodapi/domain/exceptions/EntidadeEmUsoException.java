package br.com.algaworks.algafoodapi.domain.exceptions;

import lombok.Getter;

@Getter
public class EntidadeEmUsoException extends RuntimeException {

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
