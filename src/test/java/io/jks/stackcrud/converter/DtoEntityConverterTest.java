package io.jks.stackcrud.converter;

import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.entitypg.PessoaEntity;
import io.jks.stackcrud.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class DtoEntityConverterTest {

    @InjectMocks
    private DtoEntityConverter converter;
    @Mock
    private ModelMapper mapper;

    @Test
    void PessoaDtoToEntityOk(){
        PessoaDTO dto = Constants.getPessoaDTO();
        PessoaEntity entity = converter.pessoaDtoToEntity(dto);
        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getDocumento(), entity.getDocumento());
        assertEquals(dto.getMae(), entity.getMae());
        assertEquals(dto.getPai(), entity.getPai());
        assertEquals(dto.getNascimento(),entity.getNascimento());
        assertEquals(dto.getCivil(), entity.getCivil());
        assertEquals(dto.getGenero(), entity.getGenero());
        assertEquals(dto.getEndereco(), entity.getEndereco());
        assertEquals(dto.getComplemento(), entity.getComplemento());
        assertEquals(dto.getBairro(), entity.getBairro());
        assertEquals(dto.getCidade(), entity.getCidade());
        assertEquals(dto.getMae(), entity.getMae());
        assertEquals(dto.getEstado(), entity.getEstado());
        assertEquals(dto.getTelefone(), entity.getTelefone());
        assertEquals(dto.getCelular(), entity.getCelular());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getCep(), entity.getCep());
    }

    @Test
    void PessoaEntityToDtoOk(){
        PessoaEntity entity = Constants.getPessoaEntity();
        PessoaDTO dto = converter.pessoaEntityToDto(entity);
        assertNotNull(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getDocumento(), entity.getDocumento());
        assertEquals(dto.getMae(), entity.getMae());
        assertEquals(dto.getPai(), entity.getPai());
        assertEquals(dto.getNascimento(),entity.getNascimento());
        assertEquals(dto.getCivil(), entity.getCivil());
        assertEquals(dto.getGenero(), entity.getGenero());
        assertEquals(dto.getEndereco(), entity.getEndereco());
        assertEquals(dto.getComplemento(), entity.getComplemento());
        assertEquals(dto.getBairro(), entity.getBairro());
        assertEquals(dto.getCidade(), entity.getCidade());
        assertEquals(dto.getMae(), entity.getMae());
        assertEquals(dto.getEstado(), entity.getEstado());
        assertEquals(dto.getTelefone(), entity.getTelefone());
        assertEquals(dto.getCelular(), entity.getCelular());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getCep(), entity.getCep());
    }
}
