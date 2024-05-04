package com.gontijo.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PautaInclusaoDto {

    @NotBlank(message = "Pauta não incluída! O título é obrigatório!")
    String title;

    String description;
}
