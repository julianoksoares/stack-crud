package io.jks.stackcrud.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerTest {

    @InjectMocks
    private ExceptionsHandlers handlers;

    @Test
    void notFound() {
        NotFoundException ex = new NotFoundException("Erro");
        Mono<ErrorInfo> error = handlers.notFound(ex);
        StepVerifier.create(error)
                .assertNext(e -> {
                    assertNotNull(e);
                    assertNotNull(e.getCodigo());
                    assertNotNull(e.getDescricao());
                    assertNull(e.getDetalhes());
                })
                .expectComplete()
                .verify();
    }


    @Test
    void codigoDuplicado() {
        DocumentoDuplicadoException ex = new DocumentoDuplicadoException("Erro");
        Mono<ErrorInfo> error = handlers.codigoDuplicado(ex);
        StepVerifier.create(error)
                .assertNext(e -> {
                    assertNotNull(e);
                    assertNotNull(e.getCodigo());
                    assertNotNull(e.getDescricao());
                    assertNull(e.getDetalhes());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void server() {
        Exception ex = new Exception("Erro");
        Mono<ErrorInfo> error = handlers.server(ex);
        StepVerifier.create(error)
                .assertNext(e -> {
                    assertNotNull(e);
                    assertNotNull(e.getCodigo());
                    assertNotNull(e.getDescricao());
                    assertNull(e.getDetalhes());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void valorAlteraNaoConfere() {
        ValorAlteraNaoConfereException ex = new ValorAlteraNaoConfereException("Erro");
        Mono<ErrorInfo> error = handlers.valorAlteraNaoConfere(ex);
        StepVerifier.create(error)
                .assertNext(e -> {
                    assertNotNull(e);
                    assertNotNull(e.getCodigo());
                    assertNotNull(e.getDescricao());
                    assertNull(e.getDetalhes());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void notValidInput() {
        ServerWebInputException ex = mock(ServerWebInputException.class);
        Mono<ErrorInfo> error = handlers.notValidInput(ex);
        StepVerifier.create(error)
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();
    }
}