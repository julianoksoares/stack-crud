package io.jks.stackcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    private Long id;
    @NotBlank(message = "Nome da pessoa não pode estar em branco")
    private String nome;
    @NotBlank(message = "Documento da pessoa não pode estar em branco")
    private String documento;
    private String mae;
    private String pai;
    private Date nascimento;
    private String civil;
    private String genero;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String email;
    private String cep;
}
