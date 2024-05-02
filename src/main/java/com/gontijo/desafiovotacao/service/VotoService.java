package com.gontijo.desafiovotacao.service;

import com.gontijo.desafiovotacao.dto.VotoMapper;
import com.gontijo.desafiovotacao.error.ResourceNotFoudException;
import com.gontijo.desafiovotacao.error.UnprocessableEntityException;
import com.gontijo.desafiovotacao.model.Resultado;
import com.gontijo.desafiovotacao.model.Voto;
import com.gontijo.desafiovotacao.repository.PautaRepository;
import com.gontijo.desafiovotacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static com.gontijo.desafiovotacao.enumerator.VoteOptions.NO;
import static com.gontijo.desafiovotacao.enumerator.VoteOptions.YES;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final VotoMapper votoMapper;


    public Voto incluirVoto(Long id, Voto voto) {

        var pauta = pautaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoudException(MessageFormat.format("Pauta com o ID:{0} não encontrada", id.toString())));

        var expiration = pauta.getExpirationDateTime();

        if (isNull(expiration)) {
            throw new UnprocessableEntityException(MessageFormat.format("A sessão da pauta com ID:{0} ainda não foi aberta", id));
        }
        if (LocalDateTime.now().isAfter(expiration)) {
            throw new UnprocessableEntityException(MessageFormat.format("A sessão da pauta com ID:{0} já foi fechada em {1}", id, expiration.toLocalDate().toString()));
        }

        voto.setCreatedAt(LocalDateTime.now());
        voto.setPauta(pauta);
        return votoRepository.save(voto);
    }

    public Resultado consultarResultadoVotacaoPauta(Long id) {

        var pauta = pautaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoudException(MessageFormat.format("Pauta com o ID:{0} não encontrada", id.toString())));
        var votosSim = votoRepository.findByPautaIdAndVote(id, YES.getVote()).size();
        var votosNao = votoRepository.findByPautaIdAndVote(id, NO.getVote()).size();

        return votoMapper.mapToResultado(votosSim, votosNao);
    }

}
