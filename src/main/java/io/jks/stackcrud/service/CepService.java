package io.jks.stackcrud.service;

import io.jks.stackcrud.client.CepClient;
import io.jks.stackcrud.client.CepRetorno;
import io.jks.stackcrud.converter.ToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class CepService {
    private final CepClient client;
    private final ToJsonConverter converter;

    public Mono<Boolean> validaCEP(String nroCEP) {
        if (StringUtils.isNotBlank(nroCEP)) {
            return client.getCep(nroCEP)
                    .map(CepRetorno::getOk)
                    .doOnSuccess(ret -> log.info(converter.objToJson (ret)))
                    .doOnError(e -> log.error(e.getMessage()));
        } else {
            return Mono.just(true);
        }
    }

}
