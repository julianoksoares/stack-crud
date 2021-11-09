package io.jks.stackcrud.service;

import io.jks.stackcrud.entitymg.Trace;
import io.jks.stackcrud.repository.TraceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraceServiceTest {

    @InjectMocks
    private TraceService service;
    @Mock
    private TraceRepository repository;

    @Test
    void salvarOK() {
        Trace trace = Trace.builder()
                .id("111")
                        .conteudo("teste de conteudo")
                                .data(LocalDateTime.now())
                                        .evento("teste")
                                                .key("key1").build();
        when(this.repository.save(any())).thenReturn(Mono.just(trace));
        Mono<Void> retorno = service.save(trace);
        StepVerifier.create(retorno)
                .expectComplete()
                .verify();
    }
    @Test
    void error() {
        Trace trace = Trace.builder()
                .id("111")
                .conteudo("teste de conteudo")
                .data(LocalDateTime.now())
                .evento("teste")
                .key("key1").build();
        when(this.repository.save(any())).thenReturn(Mono.empty());
        Mono<Void> retorno = service.save(trace);
        StepVerifier.create(retorno)
                .expectError()
                .verify();
    }

}
