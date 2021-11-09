package io.jks.stackcrud.service;

import io.jks.stackcrud.converter.DtoEntityConverter;
import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.event.PessoaProducer;
import io.jks.stackcrud.exception.CEPInvalidoException;
import io.jks.stackcrud.exception.DocumentoDuplicadoException;
import io.jks.stackcrud.exception.NotFoundException;
import io.jks.stackcrud.exception.ValorAlteraNaoConfereException;
import io.jks.stackcrud.repository.PessoaRepository;
import io.jks.stackcrud.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @InjectMocks
    private PessoaService service;
    @Mock
    private PessoaRepository repository;
    @Mock
    private DtoEntityConverter converter;

    @Mock
    private PessoaProducer producer;

    @Mock
    private CepService cepService;

    @Test
    void buscarPessoaIDOK() {
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.converter.pessoaEntityToDto(any())).thenReturn(Constants.getPessoaDTO());
        Mono<PessoaDTO> Pessoa = service.get(Constants.ID);
        StepVerifier.create(Pessoa)
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getPai());
                    assertNotNull(t.getNascimento());
                    assertNotNull(t.getCivil());
                    assertNotNull(t.getGenero());
                    assertNotNull(t.getEndereco());
                    assertNotNull(t.getComplemento());
                    assertNotNull(t.getBairro());
                    assertNotNull(t.getCidade());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getEstado());
                    assertNotNull(t.getTelefone());
                    assertNotNull(t.getCelular());
                    assertNotNull(t.getEmail());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void buscarPessoaIDNotFound() {
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.empty());
        Mono<PessoaDTO> Pessoa = service.get(Constants.ID);
        StepVerifier.create(Pessoa)
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void buscarPessoaDocumentoOK() {
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.converter.pessoaEntityToDto(any())).thenReturn(Constants.getPessoaDTO());
        Mono<PessoaDTO> Pessoa = service.getByDocumento(Constants.DOCUMENTO);
        StepVerifier.create(Pessoa)
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getPai());
                    assertNotNull(t.getNascimento());
                    assertNotNull(t.getCivil());
                    assertNotNull(t.getGenero());
                    assertNotNull(t.getEndereco());
                    assertNotNull(t.getComplemento());
                    assertNotNull(t.getBairro());
                    assertNotNull(t.getCidade());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getEstado());
                    assertNotNull(t.getTelefone());
                    assertNotNull(t.getCelular());
                    assertNotNull(t.getEmail());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void buscarPessoaDocumentoNotFound() {
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.empty());
        Mono<PessoaDTO> Pessoa = service.getByDocumento(Constants.DOCUMENTO);
        StepVerifier.create(Pessoa)
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void salvarPessoaOK() {
        when(this.cepService.validaCEP(any())).thenReturn(Mono.just(true));
        when(this.repository.existsByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(false));
        when(this.converter.pessoaDtoToEntity(any())).thenReturn(Constants.getPessoaEntity());
        when(this.converter.pessoaEntityToDto(any())).thenReturn(Constants.getPessoaDTO());
        when(this.repository.save(Constants.getPessoaEntity())).thenReturn(Mono.just(Constants.getPessoaEntity()));
        Mono<PessoaDTO> Pessoa = service.save(Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getPai());
                    assertNotNull(t.getNascimento());
                    assertNotNull(t.getCivil());
                    assertNotNull(t.getGenero());
                    assertNotNull(t.getEndereco());
                    assertNotNull(t.getComplemento());
                    assertNotNull(t.getBairro());
                    assertNotNull(t.getCidade());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getEstado());
                    assertNotNull(t.getTelefone());
                    assertNotNull(t.getCelular());
                    assertNotNull(t.getEmail());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void salvarPessoaERROCEP() {
        when(this.cepService.validaCEP(any())).thenReturn(Mono.just(false));
        when(this.repository.existsByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(false));
        Mono<PessoaDTO> Pessoa = service.save(Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(CEPInvalidoException.class)
                .verify();
    }

    @Test
    void salvarPessoaDocumentoDuplicado() {
        when(this.repository.existsByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(true));
        Mono<PessoaDTO> Pessoa = service.save(Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(DocumentoDuplicadoException.class)
                .verify();
    }

    @Test
    void udpatePessoaIDOK() {
        when(this.cepService.validaCEP(any())).thenReturn(Mono.just(true));
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.converter.pessoaDtoToEntity(any())).thenReturn(Constants.getPessoaEntity());
        when(this.converter.pessoaEntityToDto(any())).thenReturn(Constants.getPessoaDTO());
        when(this.repository.save(Constants.getPessoaEntity())).thenReturn(Mono.just(Constants.getPessoaEntity()));
        Mono<PessoaDTO> Pessoa = service.update(Constants.ID, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getPai());
                    assertNotNull(t.getNascimento());
                    assertNotNull(t.getCivil());
                    assertNotNull(t.getGenero());
                    assertNotNull(t.getEndereco());
                    assertNotNull(t.getComplemento());
                    assertNotNull(t.getBairro());
                    assertNotNull(t.getCidade());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getEstado());
                    assertNotNull(t.getTelefone());
                    assertNotNull(t.getCelular());
                    assertNotNull(t.getEmail());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void udpatePessoaIDCEP() {
        when(this.cepService.validaCEP(any())).thenReturn(Mono.just(false));
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        Mono<PessoaDTO> Pessoa = service.update(Constants.ID, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(CEPInvalidoException.class)
                .verify();
    }

    @Test
    void udpatePessoaIdNaoConfere() {
        Mono<PessoaDTO> Pessoa = service.update(Constants.ID + 9, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(ValorAlteraNaoConfereException.class)
                .verify();
    }

    @Test
    void udpatePessoaIDDocumentoDuplicado() {
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntityUp()));
        Mono<PessoaDTO> Pessoa = service.update(Constants.ID, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(DocumentoDuplicadoException.class)
                .verify();
    }

    @Test
    void udpatePessoaDocumentoOK() {
        when(this.cepService.validaCEP(any())).thenReturn(Mono.just(true));
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.converter.pessoaDtoToEntity(any())).thenReturn(Constants.getPessoaEntity());
        when(this.converter.pessoaEntityToDto(any())).thenReturn(Constants.getPessoaDTO());
        when(this.repository.save(Constants.getPessoaEntity())).thenReturn(Mono.just(Constants.getPessoaEntity()));
        Mono<PessoaDTO> Pessoa = service.updateByDocumento(Constants.DOCUMENTO, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getEstado());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void udpatePessoaCodeNaoConfere() {
        Mono<PessoaDTO> Pessoa = service.updateByDocumento(Constants.ID + "9", Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(ValorAlteraNaoConfereException.class)
                .verify();
    }

    @Test
    void udpatePessoaCodePessoaNaoExiste() {
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.empty());
        Mono<PessoaDTO> Pessoa = service.updateByDocumento(Constants.DOCUMENTO, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void udpatePessoaCodeDocumentoDuplicado() {
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntityUp()));
        Mono<PessoaDTO> Pessoa = service.updateByDocumento(Constants.DOCUMENTO, Constants.getPessoaDTO());
        StepVerifier.create(Pessoa)
                .expectError(ValorAlteraNaoConfereException.class)
                .verify();
    }

    @Test
    void deleteIDOk() {
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.repository.deleteById(Constants.ID)).thenReturn(Mono.empty());
        Mono<Void> delete = service.delete(Constants.ID);
        StepVerifier.create(delete)
                .expectComplete()
                .verify();
    }

    @Test
    void deleteIDNaoEncontrado() {
        when(this.repository.findById(Constants.ID)).thenReturn(Mono.empty());
        Mono<Void> delete = service.delete(Constants.ID);
        StepVerifier.create(delete)
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void deleteCodeOk() {
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(Constants.getPessoaEntity()));
        when(this.repository.deleteByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.empty());
        Mono<Void> delete = service.deleteByDocumento(Constants.DOCUMENTO);
        StepVerifier.create(delete)
                .expectComplete()
                .verify();
    }

    @Test
    void deleteDocumentoNaoEncontrado() {
        when(this.repository.findByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.empty());
        Mono<Void> delete = service.deleteByDocumento(Constants.DOCUMENTO);
        StepVerifier.create(delete)
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void existe() {
        when(this.repository.existsById(Constants.ID)).thenReturn(Mono.just(true));
        Mono<Boolean> existeRet = service.existe(Constants.ID);
        StepVerifier.create(existeRet)
                .assertNext(Assertions::assertTrue)
                .expectComplete()
                .verify();
    }

    @Test
    void existeNao() {
        when(this.repository.existsById(Constants.ID)).thenReturn(Mono.just(false));
        Mono<Boolean> existeRet = service.existe(Constants.ID);
        StepVerifier.create(existeRet)
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();
    }

    @Test
    void existeDocumento() {
        when(this.repository.existsByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(true));
        Mono<Boolean> existeRet = service.existeByDocumento(Constants.DOCUMENTO);
        StepVerifier.create(existeRet)
                .assertNext(Assertions::assertTrue)
                .expectComplete()
                .verify();
    }

    @Test
    void existeDocumentoNao() {
        when(this.repository.existsByDocumento(Constants.DOCUMENTO)).thenReturn(Mono.just(false));
        Mono<Boolean> existeRet = service.existeByDocumento(Constants.DOCUMENTO);
        StepVerifier.create(existeRet)
                .assertNext(Assertions::assertFalse)
                .expectComplete()
                .verify();
    }

    @Test
    void listarPessoaOK() {
        when(this.repository.findAll()).thenReturn(Flux.fromIterable(Constants.lstPessoaEntity()));
        when(this.converter.pessoaEntityToDto(any())).thenReturn(Constants.getPessoaDTO());
        Flux<PessoaDTO> Pessoas = service.all();
        StepVerifier.create(Pessoas)
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getPai());
                    assertNotNull(t.getNascimento());
                    assertNotNull(t.getCivil());
                    assertNotNull(t.getGenero());
                    assertNotNull(t.getEndereco());
                    assertNotNull(t.getComplemento());
                    assertNotNull(t.getBairro());
                    assertNotNull(t.getCidade());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getEstado());
                    assertNotNull(t.getTelefone());
                    assertNotNull(t.getCelular());
                    assertNotNull(t.getEmail());
                })
                .assertNext(t -> {
                    assertNotNull(t);
                    assertNotNull(t.getId());
                    assertNotNull(t.getNome());
                    assertNotNull(t.getDocumento());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getPai());
                    assertNotNull(t.getNascimento());
                    assertNotNull(t.getCivil());
                    assertNotNull(t.getGenero());
                    assertNotNull(t.getEndereco());
                    assertNotNull(t.getComplemento());
                    assertNotNull(t.getBairro());
                    assertNotNull(t.getCidade());
                    assertNotNull(t.getMae());
                    assertNotNull(t.getEstado());
                    assertNotNull(t.getTelefone());
                    assertNotNull(t.getCelular());
                    assertNotNull(t.getEmail());
                })
                .expectComplete()
                .verify();
    }
}
