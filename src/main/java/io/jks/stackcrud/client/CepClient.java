package io.jks.stackcrud.client;

import io.jks.stackcrud.converter.ToJsonConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CepClient {
    @Value("${cep.url}")
    private String url;
    @Autowired
    WebClient webClient;
    @Autowired
    ToJsonConverter converter;

    public Mono<CepRetorno> getCep(String nroCEP){
        return webClient.get()
                .uri(url + "/{nro}.json", nroCEP)
                .retrieve()
                .bodyToMono(CepRetorno.class)
                .doOnSuccess(cepRetorno -> log.info(converter.objToJson (cepRetorno)));
    }

}
