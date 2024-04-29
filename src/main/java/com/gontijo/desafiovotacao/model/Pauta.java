package com.gontijo.desafiovotacao.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Pauta {

    String title;

    String description;
}
