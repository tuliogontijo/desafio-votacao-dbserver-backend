package com.gontijo.desafiovotacao.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoteOptions {

    YES("Sim"),
    NO("Não");

    @JsonValue
    private final String vote;
}
