package com.gontijo.desafiovotacao.dto;

import com.gontijo.desafiovotacao.model.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    Pauta pautaInclusaoDtoToPauta(PautaInclusaoDto pautaInclusaoDto);

}

