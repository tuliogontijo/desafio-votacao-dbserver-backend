package com.gontijo.desafiovotacao.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Resultado {

    Integer votesYes;

    Integer votesNo;

    Boolean approved;
}
