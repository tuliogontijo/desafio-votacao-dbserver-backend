package com.gontijo.desafiovotacao.service;

import com.gontijo.desafiovotacao.error.ResourceNotFoudException;
import com.gontijo.desafiovotacao.error.UnprocessableEntityException;
import com.gontijo.desafiovotacao.model.Pauta;
import com.gontijo.desafiovotacao.repository.PautaRepository;
import com.gontijo.desafiovotacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    public Pauta incluir(Pauta pauta) {

        pauta.setCreatedAt(LocalDateTime.now());
        return pautaRepository.save(pauta);
    }

    public List<Pauta> consultarTodos() {

        return pautaRepository.findAll();
    }

    public void abrirSessao(Long id, Integer duration) {

        var pauta = pautaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoudException(MessageFormat.format("Pauta com o ID:{0} não encontrada", id.toString())));

        if (nonNull(pauta.getSessionOpennigDateTime())) {

            throw new UnprocessableEntityException(MessageFormat.format("A sessão da pauta com ID:{0} já foi aberta", id));
        }

        pauta.setSessionOpennigDateTime(LocalDateTime.now());

        var expirationDateTime = nonNull(duration) ? LocalDateTime.now().plusMinutes(duration) : LocalDateTime.now().plusMinutes(1);

        pauta.setExpirationDateTime(expirationDateTime);

        pautaRepository.save(pauta);
    }
}
