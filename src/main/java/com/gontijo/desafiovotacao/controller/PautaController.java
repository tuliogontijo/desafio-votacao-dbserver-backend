package com.gontijo.desafiovotacao.controller;

import com.gontijo.desafiovotacao.dto.PautaInclusaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

    //private final PautaMapper mapper;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PautaInclusaoDto incluir(@RequestBody PautaInclusaoDto pautaInclusaoDto) {


        return null;
    }
}
