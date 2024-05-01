package com.gontijo.desafiovotacao.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResultadoDto {

    Integer votesYes;

    Integer votesNo;

    Boolean approved;
}
