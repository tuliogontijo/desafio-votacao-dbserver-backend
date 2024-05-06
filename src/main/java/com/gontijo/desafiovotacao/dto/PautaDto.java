package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.enumerator.StatusSessaoPauta;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PautaDto implements Comparable<PautaDto> {

    Long id;

    @NotBlank(message = "O título da pauta é obrigatório!")
    String title;

    String description;

    LocalDateTime sessionOpennigDateTime;

    LocalDateTime expirationDateTime;

    LocalDateTime createdAt;

    StatusSessaoPauta statusSession;

    @Override
    public int compareTo(PautaDto pautaDto) {
        return Integer.compare(this.getStatusSession().getOrder(), pautaDto.getStatusSession().getOrder());

    }
}
