package com.gontijo.desafiovotacao.model;

import com.gontijo.desafiovotacao.enumerator.VoteOptions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "votos")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;

    @Enumerated(EnumType.STRING)
    VoteOptions vote;

    LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "pauta_id")
    Pauta pauta;

}
