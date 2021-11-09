package io.jks.stackcrud.service;

import io.jks.stackcrud.converter.DtoEntityConverter;
import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.entitypg.PessoaEntity;
import io.jks.stackcrud.event.PessoaProducer;
import io.jks.stackcrud.exception.DocumentoDuplicadoException;
import io.jks.stackcrud.exception.NotFoundException;
import io.jks.stackcrud.exception.ValorAlteraNaoConfereException;
import io.jks.stackcrud.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class PessoaService {
    private final PessoaRepository repository;
    private final DtoEntityConverter converter;
    private final PessoaProducer cidadeProducer;

    public Mono<PessoaDTO> get(final Long id) {
        return Mono.just(id)
                .flatMap(this.repository::findById)
                .switchIfEmpty(Mono.error(new NotFoundException("Pessoa não encontrada para o id " + id)))
                .map(converter::pessoaEntityToDto)
                .doOnSuccess(c -> cidadeProducer.send("get", c))
                .doOnError(ex -> log.error(ex.getMessage()));

    }

    public Mono<PessoaDTO> getByDocumento(final String documento) {
        return Mono.just(documento)
                .flatMap(this.repository::findByDocumento)
                .map(converter::pessoaEntityToDto)
                .switchIfEmpty(Mono.error(new NotFoundException("Pessoa não encontrada para o documento " + documento)))
                .doOnSuccess(c -> cidadeProducer.send("getByDocumento", c))
                .doOnError(ex -> log.error(ex.getMessage()));

    }

    public Mono<PessoaDTO> save(final PessoaDTO pessoa) {
        return this.repository.existsByDocumento(pessoa.getDocumento())
                .filter(exist -> !exist)
                .switchIfEmpty(Mono.error(new DocumentoDuplicadoException("Já existe uma pessoa com o documento " + pessoa.getDocumento())))
                .map(f-> converter.pessoaDtoToEntity(pessoa))
                .flatMap(this.repository::save)
                .map(converter::pessoaEntityToDto)
                .doOnSuccess(c -> cidadeProducer.send("save", c));
    }

    private Mono<PessoaDTO> validaPessoaUpdateID(final Long id, final PessoaDTO pessoa) {
        return Mono.just(id)
                .map(i -> {
                    if (!i.equals(pessoa.getId())) {
                        throw new ValorAlteraNaoConfereException("Id não confere com o ID do objeto a ser alterado");
                    } else {
                        return pessoa;
                    }
                });
    }

    private Mono<PessoaDTO> validaPessoaExiste(final PessoaDTO pessoa) {
        return Mono.just(pessoa)
                .map(PessoaDTO::getId)
                .flatMap(this.repository::findById)
                .switchIfEmpty(Mono.error(new NotFoundException("Cidade a ser alterada não encontrada")))
                .map(c -> pessoa);
    }

    private Mono<PessoaDTO> validaCidadeMesmoDocumento(final PessoaDTO cidade) {
        return Mono.just(cidade)
                .map(PessoaDTO::getDocumento)
                .flatMap(this.repository::findByDocumento)
                .map(c -> {
                    if (!c.getId().equals(cidade.getId())){
                        throw new DocumentoDuplicadoException("Código da pessoa a ser alterado já existe com o id: " + c.getId());
                    }
                    return cidade;
                }).thenReturn(cidade);
    }

    public Mono<PessoaDTO> update(final Long id, final PessoaDTO pessoa) {
        return validaPessoaUpdateID(id, pessoa)
                .flatMap(this::validaPessoaExiste)
                .flatMap(this::validaCidadeMesmoDocumento)
                .map(converter::pessoaDtoToEntity)
                .flatMap(repository::save)
                .map(converter::pessoaEntityToDto)
                .doOnSuccess(c -> cidadeProducer.send("update", c));
    }


    private Mono<PessoaDTO> validaPessoaUpdateCode(final String documento, final PessoaDTO pessoa) {
        return Mono.just(documento)
                .map(i -> {
                    if (!i.equals(pessoa.getDocumento())) {
                        throw new ValorAlteraNaoConfereException("Documento não confere com o Documento do objeto");
                    } else {
                        return pessoa;
                    }
                });
    }

    private Mono<PessoaDTO> validaPessoaExisteDocumento(final PessoaDTO pessoa) {
        return Mono.just(pessoa)
                .map(PessoaDTO::getDocumento)
                .flatMap(this.repository::findByDocumento)
                .switchIfEmpty(Mono.error(new NotFoundException("Pessoa a ser alterada não encontrada pelo documento " + pessoa.getDocumento())))
                .map(c -> {
                    if (!c.getId().equals(pessoa.getId())){
                        throw new ValorAlteraNaoConfereException("Documento da pessoa a ser alterada não confere com o Documento existente");
                 }
                    return pessoa;
                });
    }

    public Mono<PessoaDTO> updateByDocumento(final String documento, final PessoaDTO pessoa) {
        return validaPessoaUpdateCode(documento, pessoa)
                .flatMap(this::validaPessoaExisteDocumento)
                .map(converter::pessoaDtoToEntity)
                .flatMap(repository::save)
                .map(converter::pessoaEntityToDto)
                .doOnSuccess(c -> cidadeProducer.send("updateByDocumento", c));
    }

    public Mono<Void> delete(final Long id) {
        return Mono.just(id)
                .flatMap(this.repository::findById)
                .switchIfEmpty(Mono.error(new NotFoundException("Pessoa a ser excluída não encontrada")))
                .map(PessoaEntity::getId)
                .flatMap(this.repository::deleteById)
                .doOnSuccess(c -> cidadeProducer.send("delete", id));
    }

    public Mono<Void> deleteByDocumento(final String documento) {
        return Mono.just(documento)
                .flatMap(this.repository::findByDocumento)
                .switchIfEmpty(Mono.error(new NotFoundException("Documento a ser excluída não encontrada")))
                .map(PessoaEntity::getDocumento)
                .flatMap(this.repository::deleteByDocumento)
                .doOnSuccess(c -> cidadeProducer.send("deleteByDocumento", documento));
    }

    public Mono<Boolean> existe(final Long id) {
        return this.repository.existsById(id)
                .doOnSuccess(c -> cidadeProducer.send("existe", id));
    }

    public Mono<Boolean> existeByDocumento(final String documento) {
        return this.repository.existsByDocumento(documento)
                .doOnSuccess(c -> cidadeProducer.send("existeByDocumento", documento));
    }

    public Flux<PessoaDTO> all() {
        return this.repository.findAll()
                .map(converter::pessoaEntityToDto);
    }
}
