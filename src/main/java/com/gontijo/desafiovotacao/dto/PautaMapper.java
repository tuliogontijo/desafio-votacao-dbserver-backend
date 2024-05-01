package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.enumerator.StatusSessaoPauta;
import com.gontijo.desafiovotacao.model.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    Pauta pautaInclusaoDtoToPauta(PautaInclusaoDto pautaInclusaoDto);

    List<PautaDto> pautaToListPautaDto(List<Pauta> pautas);

    @Mapping(target = "statusSession", expression = "java(getSessionStatus(pauta))")
    PautaDto pautaToPautaDto(Pauta pauta);

    default StatusSessaoPauta getSessionStatus(Pauta pauta) {

        if (isNull(pauta.getSessionOpennigDateTime())) {

            return StatusSessaoPauta.CRIADA;

        } else {

            if (pauta.getExpirationDateTime().isAfter(LocalDateTime.now())) {

                return StatusSessaoPauta.ABERTA;

            } else {

                return StatusSessaoPauta.FECHADA;
            }
        }
    }

}

