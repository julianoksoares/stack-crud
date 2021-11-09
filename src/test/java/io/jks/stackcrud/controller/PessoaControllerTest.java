package io.jks.stackcrud.controller;

import io.jks.stackcrud.converter.ToJsonConverter;
import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.exception.ErrorInfo;
import io.jks.stackcrud.service.PessoaService;
import io.jks.stackcrud.util.Constants;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = PessoaController.class)
@Log4j2
class PessoaControllerTest {

    @MockBean
    private PessoaService service;
    @MockBean
    private ToJsonConverter converter;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getId() {
        when(service.get(any())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .get()
                .uri("/api/v1/pessoa/{id}", Constants.ID)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(PessoaDTO.class)
                .value(PessoaDTO::getId, equalTo(Constants.ID))
                .value(PessoaDTO::getDocumento, equalTo(Constants.DOCUMENTO))
                .value(PessoaDTO::getNome, equalTo(Constants.NOME))
                .value(PessoaDTO::getMae, equalTo(Constants.MAE))
                .value(PessoaDTO::getPai, equalTo(Constants.PAI))
                .value(PessoaDTO::getNascimento, equalTo(Constants.NASC))
                .value(PessoaDTO::getCivil, equalTo(Constants.CIVIL))
                .value(PessoaDTO::getGenero, equalTo(Constants.GENERO))
                .value(PessoaDTO::getEndereco, equalTo(Constants.ENDERECO))
                .value(PessoaDTO::getComplemento, equalTo(Constants.COMPLEMENTO))
                .value(PessoaDTO::getBairro, equalTo(Constants.BAIRRO))
                .value(PessoaDTO::getCidade, equalTo(Constants.CIDADE))
                .value(PessoaDTO::getEstado, equalTo(Constants.ESTADO))
                .value(PessoaDTO::getTelefone, equalTo(Constants.TELEFONE))
                .value(PessoaDTO::getCelular, equalTo(Constants.CELULAR))
                .value(PessoaDTO::getEmail, equalTo(Constants.EMAIL));
        verify(this.service).get(Constants.ID);
    }

    @Test
    void getCodigo() {
        when(service.getByDocumento (any())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .get()
                .uri("/api/v1/pessoa/{documento}/documento", Constants.DOCUMENTO)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(PessoaDTO.class)
                .value(PessoaDTO::getId, equalTo(Constants.ID))
                .value(PessoaDTO::getDocumento, equalTo(Constants.DOCUMENTO))
                .value(PessoaDTO::getNome, equalTo(Constants.NOME))
                .value(PessoaDTO::getMae, equalTo(Constants.MAE))
                .value(PessoaDTO::getPai, equalTo(Constants.PAI))
                .value(PessoaDTO::getNascimento, equalTo(Constants.NASC))
                .value(PessoaDTO::getCivil, equalTo(Constants.CIVIL))
                .value(PessoaDTO::getGenero, equalTo(Constants.GENERO))
                .value(PessoaDTO::getEndereco, equalTo(Constants.ENDERECO))
                .value(PessoaDTO::getComplemento, equalTo(Constants.COMPLEMENTO))
                .value(PessoaDTO::getBairro, equalTo(Constants.BAIRRO))
                .value(PessoaDTO::getCidade, equalTo(Constants.CIDADE))
                .value(PessoaDTO::getEstado, equalTo(Constants.ESTADO))
                .value(PessoaDTO::getTelefone, equalTo(Constants.TELEFONE))
                .value(PessoaDTO::getCelular, equalTo(Constants.CELULAR))
                .value(PessoaDTO::getEmail, equalTo(Constants.EMAIL));
        verify(this.service).getByDocumento(Constants.DOCUMENTO);
    }

    @Test
    void save() {
        when(service.save(any())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .post()
                .uri("/api/v1/pessoa/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Constants.getPessoaDTO()), PessoaDTO.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(PessoaDTO.class)
                .value(PessoaDTO::getId, equalTo(Constants.ID))
                .value(PessoaDTO::getDocumento, equalTo(Constants.DOCUMENTO))
                .value(PessoaDTO::getNome, equalTo(Constants.NOME))
                .value(PessoaDTO::getMae, equalTo(Constants.MAE))
                .value(PessoaDTO::getPai, equalTo(Constants.PAI))
                .value(PessoaDTO::getNascimento, equalTo(Constants.NASC))
                .value(PessoaDTO::getCivil, equalTo(Constants.CIVIL))
                .value(PessoaDTO::getGenero, equalTo(Constants.GENERO))
                .value(PessoaDTO::getEndereco, equalTo(Constants.ENDERECO))
                .value(PessoaDTO::getComplemento, equalTo(Constants.COMPLEMENTO))
                .value(PessoaDTO::getBairro, equalTo(Constants.BAIRRO))
                .value(PessoaDTO::getCidade, equalTo(Constants.CIDADE))
                .value(PessoaDTO::getEstado, equalTo(Constants.ESTADO))
                .value(PessoaDTO::getTelefone, equalTo(Constants.TELEFONE))
                .value(PessoaDTO::getCelular, equalTo(Constants.CELULAR))
                .value(PessoaDTO::getEmail, equalTo(Constants.EMAIL));
        verify(this.service).save(any());
    }


    @Test
    void saveNok1() {
        when(service.save(any())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .post()
                .uri("/api/v1/pessoa/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Constants.getPessoaDTOVazio()), PessoaDTO.class)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(ErrorInfo.class);
    }

    @Test
    void saveNok2() {
        when(service.save(any())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .post()
                .uri("/api/v1/pessoa/")
                .contentType(MediaType.APPLICATION_JSON)
             //   .body(null)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(ErrorInfo.class);
    }

    @Test
    void update() {
        when(service.update(Constants.ID, Constants.getPessoaDTO())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .put()
                .uri("/api/v1/pessoa/{id}", Constants.ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Constants.getPessoaDTO()), PessoaDTO.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(PessoaDTO.class)
                .value(PessoaDTO::getId, equalTo(Constants.ID))
                .value(PessoaDTO::getDocumento, equalTo(Constants.DOCUMENTO))
                .value(PessoaDTO::getNome, equalTo(Constants.NOME))
                .value(PessoaDTO::getMae, equalTo(Constants.MAE))
                .value(PessoaDTO::getPai, equalTo(Constants.PAI))
                .value(PessoaDTO::getNascimento, equalTo(Constants.NASC))
                .value(PessoaDTO::getCivil, equalTo(Constants.CIVIL))
                .value(PessoaDTO::getGenero, equalTo(Constants.GENERO))
                .value(PessoaDTO::getEndereco, equalTo(Constants.ENDERECO))
                .value(PessoaDTO::getComplemento, equalTo(Constants.COMPLEMENTO))
                .value(PessoaDTO::getBairro, equalTo(Constants.BAIRRO))
                .value(PessoaDTO::getCidade, equalTo(Constants.CIDADE))
                .value(PessoaDTO::getEstado, equalTo(Constants.ESTADO))
                .value(PessoaDTO::getTelefone, equalTo(Constants.TELEFONE))
                .value(PessoaDTO::getCelular, equalTo(Constants.CELULAR))
                .value(PessoaDTO::getEmail, equalTo(Constants.EMAIL));
        verify(this.service).update(Constants.ID, Constants.getPessoaDTO());
    }

    @Test
    void updateDocumento() {
        when(service.updateByDocumento(Constants.DOCUMENTO, Constants.getPessoaDTO())).thenReturn(Mono.just(Constants.getPessoaDTO()));
        webTestClient
                .put()
                .uri("/api/v1/pessoa/{documento}/documento", Constants.DOCUMENTO)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Constants.getPessoaDTO()), PessoaDTO.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(APPLICATION_JSON_VALUE)
                .expectBody(PessoaDTO.class)
                .value(PessoaDTO::getId, equalTo(Constants.ID))
                .value(PessoaDTO::getDocumento, equalTo(Constants.DOCUMENTO))
                .value(PessoaDTO::getNome, equalTo(Constants.NOME))
                .value(PessoaDTO::getMae, equalTo(Constants.MAE))
                .value(PessoaDTO::getPai, equalTo(Constants.PAI))
                .value(PessoaDTO::getNascimento, equalTo(Constants.NASC))
                .value(PessoaDTO::getCivil, equalTo(Constants.CIVIL))
                .value(PessoaDTO::getGenero, equalTo(Constants.GENERO))
                .value(PessoaDTO::getEndereco, equalTo(Constants.ENDERECO))
                .value(PessoaDTO::getComplemento, equalTo(Constants.COMPLEMENTO))
                .value(PessoaDTO::getBairro, equalTo(Constants.BAIRRO))
                .value(PessoaDTO::getCidade, equalTo(Constants.CIDADE))
                .value(PessoaDTO::getEstado, equalTo(Constants.ESTADO))
                .value(PessoaDTO::getTelefone, equalTo(Constants.TELEFONE))
                .value(PessoaDTO::getCelular, equalTo(Constants.CELULAR))
                .value(PessoaDTO::getEmail, equalTo(Constants.EMAIL));
        verify(this.service).updateByDocumento(Constants.DOCUMENTO, Constants.getPessoaDTO());
    }

    @Test
    void delete() {
        when(service.delete(Constants.ID)).thenReturn(Mono.empty());
        webTestClient
                .delete()
                .uri("/api/v1/pessoa/{id}", Constants.ID)
                .exchange()
                .expectStatus()
                .isNoContent();
        verify(this.service).delete(Constants.ID);
    }

    @Test
    void deleteDocumento() {
        when(service.deleteByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.empty());
        webTestClient
                .delete()
                .uri("/api/v1/pessoa/{documento}/documento", Constants.DOCUMENTO)
                .exchange()
                .expectStatus()
                .isNoContent();
        verify(this.service).deleteByDocumento(Constants.DOCUMENTO);
    }
}
