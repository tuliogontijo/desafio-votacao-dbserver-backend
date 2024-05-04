package com.gontijo.desafiovotacao.controller;

import com.gontijo.desafiovotacao.dto.*;
import com.gontijo.desafiovotacao.service.PautaService;
import com.gontijo.desafiovotacao.service.VotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/pautas")
public class PautaController {

    private final PautaService pautaService;
    private final PautaMapper pautaMapper;
    private final VotoService votoService;
    private final VotoMapper votoMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PautaDto incluir(
            @RequestBody @Valid PautaInclusaoDto pautaInclusaoDto) {

        log.info("😎 Recebendo requisição para incluir pauta. Dados: {}", pautaInclusaoDto);

        var pauta = pautaService.incluir(pautaMapper.pautaInclusaoDtoToPauta(pautaInclusaoDto));

        log.info("Pauta incluída: {}", pauta);

        return pautaMapper.pautaToPautaDto(pauta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<PautaDto> consultarTodos() {

        log.info("Recebendo requisição para consultar todas as pautas");

        var pautas = pautaService.consultarTodos();

        var pautasDto = pautaMapper.pautaToListPautaDto(pautas);

        log.info("Pautas: {}", pautasDto);

        return pautasDto;
    }

    @CrossOrigin
    @PatchMapping("{id}/sessao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void abrirSessao(
            @PathVariable Long id,
            @RequestBody SessaoDto sessaoDto) {

        log.info("Recebendo requisição para abrir sessão da pauta com ID:{}. Dados: {}", id, sessaoDto);

        pautaService.abrirSessao(id, sessaoDto.getDuration());

        log.info("Pauta com ID:{} aberta", id);
    }

    @PostMapping("{id}/voto")
    @ResponseStatus(HttpStatus.CREATED)
    VotoDto incluirVoto(@PathVariable Long id, @RequestBody @Valid VotoInclusaoDTO votoInclusaoDTO) {

        log.info("Recebendo requisição para inclusão de voto na pauta com ID:{}. Dados: {}", id, votoInclusaoDTO);

        var voto = votoService.incluirVoto(id, votoMapper.votoInclusaoDtoToVoto(votoInclusaoDTO));

        log.info("Voto incluído: {}", voto);

        return votoMapper.votoToVotoDto(voto);
    }

    @GetMapping("{id}/resultado")
    @ResponseStatus(HttpStatus.OK)
    ResultadoDto consultarResultadoVotacaoPauta(@PathVariable Long id) {

        log.info("Recebendo requisição para consultar o resultado da pauta de ID:{}", id);

        var resultado = votoService.consultarResultadoVotacaoPauta(id);

        log.info("Resultado encontrado: {}", resultado);

        return votoMapper.resultadoToResultadoDto(resultado);
    }

}