package io.jks.stackcrud.repository;

import io.jks.stackcrud.entitypg.PessoaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PessoaRepository extends ReactiveCrudRepository<PessoaEntity, Long> {

    Mono<PessoaEntity> findByDocumento(String documento);

    Mono<Boolean> existsByDocumento(String documento);

    Mono<Void> deleteByDocumento(String documento);
}
