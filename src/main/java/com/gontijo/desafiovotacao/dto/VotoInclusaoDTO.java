package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.enumerator.VoteOptions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VotoInclusaoDTO {

    @NotBlank(message = "O CPF é obrigatório!")
    String cpf;

    @NotNull(message = "O voto é obrigatório!")
    VoteOptions vote;
}
