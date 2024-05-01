package com.gontijo.desafiovotacao.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class SessaoDto {

    Integer duration;
}
