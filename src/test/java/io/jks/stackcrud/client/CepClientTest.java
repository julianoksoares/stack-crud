package io.jks.stackcrud.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
public class CepClientTest  {
    private static final String cep = "94760000";
    @Autowired
    private CepClient client;

    @Test
    void testeOk(){
        stubFor(get(urlEqualTo("/" +cep +".json"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
                        .withBodyFile("cepok.json"))
        );
        Mono<CepRetorno> retorno = client.getCep(cep);
        StepVerifier.create(retorno)
                .assertNext( t ->{
                    assertNotNull(t);
                    assertNotNull(t.getAddress());
                    assertNotNull(t.getCity());
                    assertNotNull(t.getCode());
                    assertNotNull(t.getDistrict());
                    assertNotNull(t.getOk());
                    assertNotNull(t.getState());
                    assertNotNull(t.getStatusText());
                })
                .verifyComplete();

    }




}
