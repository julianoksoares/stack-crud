package io.jks.stackcrud.event;

import io.jks.stackcrud.entitymg.Trace;
import io.jks.stackcrud.service.TraceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Log4j2
@AllArgsConstructor
public class TracePessoaConsumer {


    private final TraceService service;

    @KafkaListener(topics = "person-trace", groupId = "1")
    public void consume(@Payload String valor,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header("EVENTO") String event) {
        service.save(Trace.builder()
                .key(key)
                .data(LocalDateTime.now())
                .conteudo(valor)
                .evento(event).build()).block();
    }
}
