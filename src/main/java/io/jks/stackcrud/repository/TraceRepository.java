package io.jks.stackcrud.repository;

import io.jks.stackcrud.entitymg.Trace;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TraceRepository extends ReactiveMongoRepository<Trace, String> {
}
