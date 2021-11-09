package io.jks.stackcrud.converter;

import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.entitypg.PessoaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DtoEntityConverter {

    public PessoaDTO pessoaEntityToDto(PessoaEntity pessoa) {
        return new ModelMapper().map(pessoa, PessoaDTO.class);
    }

    public PessoaEntity pessoaDtoToEntity(PessoaDTO pessoa) {
        return new ModelMapper().map(pessoa, PessoaEntity.class);
    }
}
