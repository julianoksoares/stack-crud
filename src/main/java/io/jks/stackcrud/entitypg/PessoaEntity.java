package io.jks.stackcrud.entitypg;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("pessoa")
public class PessoaEntity implements Serializable {

    @Id
    @Column("oid_pessoa")
    private Long id;
    @Column("nom_pessoa")
    private String nome;
    @Column("nro_documento")
    private String documento;
    @Column("nom_mae")
    private String mae;
    @Column("nom_pai")
    private String pai;
    @Column("dat_nascimento")
    private Date nascimento;
    @Column("tpo_civil")
    private String civil;
    @Column("tpo_genero")
    private String genero;
    @Column("des_endereco")
    private String endereco;
    @Column("des_complemento")
    private String complemento;
    @Column("nom_bairro")
    private String bairro;
    @Column("nom_cidade")
    private String cidade;
    @Column("cod_estado")
    private String estado;
    @Column("nro_telefone")
    private String telefone;
    @Column("nro_celular")
    private String celular;
    @Column("des_email")
    private String email;
    @Column("nro_cep")
    private String cep;
}
