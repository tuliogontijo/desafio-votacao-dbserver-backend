package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.model.Resultado;
import com.gontijo.desafiovotacao.model.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotoMapper {
    Voto votoInclusaoDtoToVoto(VotoInclusaoDTO votoInclusaoDTO);

    @Mapping(target = "pautaId", source = "voto.pauta.id")
    VotoDto votoToVotoDto(Voto voto);

    ResultadoDto resultadoToResultadoDto(Resultado resultado);

    @Mapping(target = "approved", expression = "java(((votesYes + votesNo) == 0) ? null : votesYes >= votesNo)")
    Resultado mapToResultado(Integer votesYes, Integer votesNo);
}
