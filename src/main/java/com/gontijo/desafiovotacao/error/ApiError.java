package com.gontijo.desafiovotacao.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

    private String message;

}
