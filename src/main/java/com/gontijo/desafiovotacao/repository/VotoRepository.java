package com.gontijo.desafiovotacao.repository;

import com.gontijo.desafiovotacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(value = "SELECT vote FROM votos WHERE pauta_id = ?1 AND vote = ?2 ", nativeQuery = true)
    List<String> findByPautaIdAndVote(Long pautaId, String vote);
}
