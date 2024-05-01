package com.gontijo.desafiovotacao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorDto {

    private String message;
}
