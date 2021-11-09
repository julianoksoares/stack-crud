package io.jks.stackcrud.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DetailError {
    private String campo;
    private String descricao;
    private Object valor;
}
