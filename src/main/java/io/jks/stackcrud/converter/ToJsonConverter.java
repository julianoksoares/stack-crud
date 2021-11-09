package io.jks.stackcrud.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jks.stackcrud.exception.ConverterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ToJsonConverter {

    private ObjectMapper mapper;

    public String objToJson(Object obj) {
        final String s;
        try {
            s = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ConverterException(e.getMessage());
        }
        return s;
    }
}
