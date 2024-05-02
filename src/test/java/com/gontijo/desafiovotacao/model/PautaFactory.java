package com.gontijo.desafiovotacao.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

public class PautaFactory {

    public static Pauta build() {

        return Pauta.builder()
                .id(gerarLong())
                .title(gerarString())
                .description(gerarString())
                .createdAt(LocalDateTime.now())
                .expirationDateTime(LocalDateTime.now())
                .sessionOpennigDateTime(LocalDateTime.now())
                .build();
    }

    private static String gerarString() {

        return RandomStringUtils.randomAlphabetic(10);
    }

    private static Long gerarLong() {

        return (long) (Math.random() * 100);
    }
}