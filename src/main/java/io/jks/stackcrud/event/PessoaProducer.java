package io.jks.stackcrud.event;

import io.jks.stackcrud.converter.ToJsonConverter;
import io.jks.stackcrud.dto.PessoaDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;

@Component
@Log4j2
public class PessoaProducer {

    private final ToJsonConverter converter;

    private final KafkaSender<String, String> sender;

    public PessoaProducer(ToJsonConverter converter, KafkaSender<String, String> sender) {
        this.converter = converter;
        this.sender = sender;
    }

    public void send(String event, PessoaDTO cidadeDTO) {
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("person-trace", cidadeDTO.getId().toString(), converter.objToJson (cidadeDTO));
        Headers headers = producerRecord.headers();
        headers.add("EVENTO", event.getBytes());
        sender
                .createOutbound()
                .send(Mono.just(producerRecord))
                .then()
                .subscribe();
     }

    public void send(String event, String id) {
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("person-trace", id, converter.objToJson (id));
        Headers headers = producerRecord.headers();
        headers.add("EVENTO", event.getBytes());
        sender
                .createOutbound()
                .send(Mono.just(producerRecord))
                .then()
                .subscribe();
    }

    public void send(String event, Long id) {
        send(event, id.toString());
    }
}