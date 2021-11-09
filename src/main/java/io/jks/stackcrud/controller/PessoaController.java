package io.jks.stackcrud.controller;

import io.jks.stackcrud.converter.ToJsonConverter;
import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.service.PessoaService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/pessoa")
@AllArgsConstructor
@Log4j2
public class PessoaController {
    private final PessoaService service;
    private final ToJsonConverter converter;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PessoaDTO> buscarPessoa(@PathVariable("id") Long id){
        log.info("buscarPessoa in: " + id);
        return this.service.get(id)
                .doOnSuccess(dto -> log.info("buscarPessoa out: " + converter.objToJson(dto)));
    }

    @GetMapping("/{documento}/documento")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PessoaDTO> buscarPessoaDocumento(@PathVariable("documento") String documento){
        log.info("buscarPessoaDocumento in: " + documento);
        return this.service.getByDocumento(documento)
                .doOnSuccess(dto -> log.info("buscarPessoaDocumento out: " + converter.objToJson(dto)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PessoaDTO> criarPessoa(@RequestBody @Valid PessoaDTO pessoa){
        log.info("criarPessoa in: " + converter.objToJson(pessoa));
        return this.service.save(pessoa)
                .doOnSuccess(dto -> log.info("criarPessoa out: " + converter.objToJson(dto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PessoaDTO> atualizarPessoa(@PathVariable("id") Long id, @RequestBody PessoaDTO pessoa){
        log.info("atualizarPessoa in: id: "+ id + " -> " + converter.objToJson(pessoa));
        return this.service.update(id, pessoa)
                .doOnSuccess(dto -> log.info("atualizarPessoa out: id: " + id + " -> " + converter.objToJson(dto)));
    }

    @PutMapping("/{documento}/documento")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PessoaDTO> atualizarPessoaDocumento(@PathVariable("documento") String documento, @RequestBody PessoaDTO pessoa){
        log.info("atualizarPessoa in: id: "+ documento + " -> " + converter.objToJson(pessoa));
        return this.service.updateByDocumento(documento, pessoa)
                .doOnSuccess(dto -> log.info("atualizarPessoaDocumento out: documento: " + documento + " -> " + converter.objToJson(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> apagarPessoa(@PathVariable("id") Long id){
        log.info("apagarPessoa in: " + id);
        return this.service.delete(id)
                .doOnSuccess(dto -> log.info("apagarPessoa out: id: " + id + " -> SUCESSO"));
    }

    @DeleteMapping("/{documento}/documento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> apagarPessoaDocumento(@PathVariable("documento") String documento){
        log.info("apagarPessoaDocumento in: " + documento);
        return this.service.deleteByDocumento(documento)
                .doOnSuccess(dto -> log.info("apagarPessoa out: documento: " + documento + " -> SUCESSO"));
    }
}
