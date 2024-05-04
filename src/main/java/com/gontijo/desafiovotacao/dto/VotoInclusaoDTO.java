package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.enumerator.VoteOptions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;


@Value
@Builder
public class VotoInclusaoDTO {

    @NotBlank(message = "Voto não registrado! O CPF é obrigatório!")
    @CPF(message = "Voto não registrado! O CPF não é válido!")
    String cpf;

    @NotNull(message = "Voto não registrado! Escolha entre 'Sim' ou 'Não''")
    VoteOptions vote;
}
