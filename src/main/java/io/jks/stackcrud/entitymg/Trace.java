package io.jks.stackcrud.entitymg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("trace")
public class Trace {
    @Id
    private String id;
    private String key;
    private String evento;
    private LocalDateTime data;
    private String conteudo;
}
