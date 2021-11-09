package io.jks.stackcrud.service;

import io.jks.stackcrud.client.CepClient;
import io.jks.stackcrud.client.CepRetorno;
import io.jks.stackcrud.converter.ToJsonConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @InjectMocks
    private CepService service;
    @Mock
    private CepClient client;
    @Mock
    private ToJsonConverter converter;

    @Test
    void getOK() {
        CepRetorno dto = CepRetorno.builder()
                .address("teste")
                .city("teste")
                .code("0000")
                .district("teste")
                .ok(true)
                .state("200")
                .statusText("Ok").build();
        when(this.client.getCep(any())).thenReturn(Mono.just(dto));
        Mono<Boolean> retorno = service.validaCEP("11111");
        StepVerifier.create(retorno)
                .assertNext(Assertions::assertTrue)
                .expectComplete()
                .verify();
    }

    @Test
    void getOKVazio() {
        Mono<Boolean> retorno = service.validaCEP("");
        StepVerifier.create(retorno)
                .assertNext(Assertions::assertTrue)
                .expectComplete()
                .verify();
    }
    @Test
    void getNOK() {
        CepRetorno dto = CepRetorno.builder()
                .address("teste")
                .city("teste")
                .code("0000")
                .district("teste")
                .ok(false)
                .state("200")
                .statusText("Ok").build();
        when(this.client.getCep(any())).thenReturn(Mono.just(dto));
        Mono<Boolean> retorno = service.validaCEP("11111");
        StepVerifier.create(retorno)
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();
    }
}
