package io.jks.stackcrud.service;

import io.jks.stackcrud.entitymg.Trace;
import io.jks.stackcrud.repository.TraceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class TraceService {
    private final TraceRepository repository;

    public Mono<Void> save(Trace entity){
        return repository.save(entity)
                .doOnError(e -> log.error("Erro ao salvar trace: " + e.getMessage()))
                .doOnSuccess( t -> log.info("Salvo trace com id: " + t.getId()))
                .then();
    }

}
