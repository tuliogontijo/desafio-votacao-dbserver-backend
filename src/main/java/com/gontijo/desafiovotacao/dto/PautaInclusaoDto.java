package com.gontijo.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PautaInclusaoDto {

    @NotBlank(message = "O título da pauta é obrigatório!")
    String title;

    String description;
}
