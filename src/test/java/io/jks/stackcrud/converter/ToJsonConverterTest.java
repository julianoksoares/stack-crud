package io.jks.stackcrud.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToJsonConverterTest {

    @InjectMocks
    private ToJsonConverter converter;
    @Mock
    private ObjectMapper mapper;

    @Test
    void ok() throws JsonProcessingException {
        PessoaDTO dto = Constants.getPessoaDTO();
        when( mapper.writeValueAsString(any())).thenReturn(new ObjectMapper().writeValueAsString(dto));
        String json = converter.objToJson(dto);
        assertNotNull(json);
    }

    @Test
    void nok() throws JsonProcessingException {
        PessoaDTO dto = Constants.getPessoaDTO();
        when( mapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("") {});
        Assertions.assertThrows(RuntimeException.class, () -> {
            converter.objToJson(dto);
        });
    }

}
