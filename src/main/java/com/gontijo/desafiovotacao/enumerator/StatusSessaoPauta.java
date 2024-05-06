package com.gontijo.desafiovotacao.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusSessaoPauta {

    CRIADA(0),
    ABERTA(1),
    FECHADA(2);

    private final int order;
}
