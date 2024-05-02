package com.gontijo.desafiovotacao.service;

import com.gontijo.desafiovotacao.error.ResourceNotFoudException;
import com.gontijo.desafiovotacao.error.UnprocessableEntityException;
import com.gontijo.desafiovotacao.model.Pauta;
import com.gontijo.desafiovotacao.model.PautaFactory;
import com.gontijo.desafiovotacao.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @Test
    @DisplayName("Deve incluir pauta com sucesso")
    public void incluirDeveIncluirComSucesso() {

        var pautaParaSalvar = Pauta.builder()
                .title("Teste titulo pauta para salvar")
                .description("Teste descrição pauta para salvar")
                .build();

        var novaPauta = Pauta.builder()
                .id(1L)
                .title("Teste titulo nova pauta")
                .description("Teste descrição nova pauta")
                .createdAt(LocalDateTime.now())
                .build();

        doReturn(novaPauta)
                .when(pautaRepository)
                .save(pautaParaSalvar);

        var retorno = pautaService.incluir(pautaParaSalvar);

        assertEquals(novaPauta, retorno);
        verify(pautaRepository, times(1)).save(pautaParaSalvar);
    }

    @Test
    @DisplayName("Deve consultar e retornar todas as pautas")
    public void consultarTodosDeveRetornarTodasAsPautas() {

        var pautas = List.of(PautaFactory.build(), PautaFactory.build());

        doReturn(pautas)
                .when(pautaRepository)
                .findAll();

        var retorno = pautaService.consultarTodos();

        assertEquals(pautas, retorno);
        verify(pautaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoudException quando buscar pauta por id e não encontrar resultado")
    public void abrirSessaoDeveLancaoResourceNotFoudExceptionQuandoNaoEncontrarPauta() {

        var id = 123L;
        var duration = 30;

        doReturn(Optional.empty())
                .when(pautaRepository)
                .findById(id);

        var exception = assertThrows(ResourceNotFoudException.class, () -> pautaService.abrirSessao(id, duration));

        var msg = MessageFormat.format("Pauta com o ID:{0} não encontrada", String.valueOf(id));
        assertEquals(msg, exception.getMessage());

        verify(pautaRepository).findById(id);
    }

    @Test
    @DisplayName("Deve lançar UnprocessableEntityException quando a sessão já tiver sido aberta")
    public void abrirSessaoDeveLancaoUnprocessableEntityExceptionQuandoASessaoJaTiverSidoAberta() {

        var id = 345L;
        var duration = 20;
        var pauta = PautaFactory.build();

        doReturn(Optional.of(pauta))
                .when(pautaRepository)
                .findById(id);

        var exception = assertThrows(UnprocessableEntityException.class, () -> pautaService.abrirSessao(id, duration));

        var msg = MessageFormat.format("A sessão da pauta com ID:{0} já foi aberta", String.valueOf(id));
        assertEquals(msg, exception.getMessage());
        verify(pautaRepository).findById(id);
    }

    @Test
    @DisplayName("Deve abrir sessão de um minuto quando a duração é nula")
    public void abrirSessaoDeveAbrirSessaoComDuracaoDeUmMinutoQuandoDuracaoEhNull() {

        var id = 111L;
        var pauta = PautaFactory.build();
        pauta.setSessionOpennigDateTime(null);

        pauta.setExpirationDateTime(LocalDateTime.now().plusMinutes(1));

        doReturn(Optional.of(pauta))
                .when(pautaRepository)
                .findById(id);

        pautaService.abrirSessao(id, null);

        verify(pautaRepository).findById(id);
        verify(pautaRepository).save(pauta);
    }

    @Test
    @DisplayName("Deve abrir sessão de um minuto quando a duração é diferente de nula")
    public void abrirSessaoDeveAbrirSessaoComDuracaoDeUmMinutoQuandoDuracaoEhDiiferenteNull() {

        var id = 222L;
        var duration = 20;
        var pauta = PautaFactory.build();
        pauta.setSessionOpennigDateTime(null);

        pauta.setExpirationDateTime(LocalDateTime.now().plusMinutes(duration));

        doReturn(Optional.of(pauta))
                .when(pautaRepository)
                .findById(id);

        pautaService.abrirSessao(id, duration);

        verify(pautaRepository).findById(id);
        verify(pautaRepository).save(pauta);
    }
}