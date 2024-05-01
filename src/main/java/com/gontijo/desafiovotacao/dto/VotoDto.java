package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.enumerator.VoteOptions;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class VotoDto {

    Long id;

    String cpf;

    VoteOptions vote;

    LocalDateTime createdAt;

    Long pautaId;

}
