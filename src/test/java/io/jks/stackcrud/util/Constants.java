package io.jks.stackcrud.util;

import io.jks.stackcrud.dto.PessoaDTO;
import io.jks.stackcrud.entitypg.PessoaEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Constants {
    public static Long ID = 2222l;
    public static String DOCUMENTO = "33333";
    public static String NOME = "TESTE JULIANO";
    public  static String MAE = "Mae do Juliano";
    public static String PAI = "Pai do Juliano";
    public static Date NASC = new Date();
    public static String CIVIL = "CASADO";
    public static String GENERO = "MASCULINO";
    public static String ENDERECO = "Endereco do Juliano";
    public static String COMPLEMENTO = "Complemento do Juliano";
    public static String BAIRRO = "Bairro do Juliano";
    public static String CIDADE = "Cidade do Juliano";
    public static String ESTADO = "RS";
    public static String TELEFONE = "5133335555";
    public static String CELULAR = "51998424121";
    public static String EMAIL = "julianoksoares@gmail.com";
    public static String CEP = "94760000";
    public static PessoaDTO getPessoaDTO(){
        return PessoaDTO.builder()
                .id(ID)
                .nome(NOME)
                .documento(DOCUMENTO)
                .mae(MAE)
                .pai(PAI)
                .nascimento(NASC)
                .civil(CIVIL)
                .genero(GENERO)
                .endereco(ENDERECO)
                .complemento(COMPLEMENTO)
                .bairro(BAIRRO)
                .cidade(CIDADE)
                .estado(ESTADO)
                .telefone(TELEFONE)
                .celular(CELULAR)
                .email(EMAIL)
                .cep(CEP)
                .build();
    }

    public static PessoaDTO getPessoaDTOVazio(){
        return PessoaDTO.builder()
                .build();
    }

    public static PessoaEntity getPessoaEntity(){
        return PessoaEntity.builder()
                .id(ID)
                .nome(NOME)
                .documento(DOCUMENTO)
                .mae(MAE)
                .pai(PAI)
                .nascimento(NASC)
                .civil(CIVIL)
                .genero(GENERO)
                .endereco(ENDERECO)
                .complemento(COMPLEMENTO)
                .bairro(BAIRRO)
                .cidade(CIDADE)
                .estado(ESTADO)
                .telefone(TELEFONE)
                .celular(CELULAR)
                .email(EMAIL)
                .cep(CEP)
                .build();
    }


    public static List<PessoaEntity> lstPessoaEntity(){
        List<PessoaEntity> lst = new ArrayList<>();
        lst.add(PessoaEntity.builder()
                .id(ID)
                .nome(NOME)
                .documento(DOCUMENTO)
                .mae(MAE)
                .pai(PAI)
                .nascimento(NASC)
                .civil(CIVIL)
                .genero(GENERO)
                .endereco(ENDERECO)
                .complemento(COMPLEMENTO)
                .bairro(BAIRRO)
                .cidade(CIDADE)
                .estado(ESTADO)
                .telefone(TELEFONE)
                .celular(CELULAR)
                .email(EMAIL)
                .build());
        lst.add(PessoaEntity.builder()
                .id(ID+1)
                .nome(NOME)
                .documento(DOCUMENTO + "1")
                .mae(MAE)
                .pai(PAI)
                .nascimento(NASC)
                .civil(CIVIL)
                .genero(GENERO)
                .endereco(ENDERECO)
                .complemento(COMPLEMENTO)
                .bairro(BAIRRO)
                .cidade(CIDADE)
                .estado(ESTADO)
                .telefone(TELEFONE)
                .celular(CELULAR)
                .email(EMAIL)
                .build());
        return lst;
    }

    public static PessoaEntity getPessoaEntityUp(){
        return PessoaEntity.builder()
                .id(ID+2)
                .nome(NOME)
                .documento(DOCUMENTO)
                .mae(MAE)
                .pai(PAI)
                .nascimento(NASC)
                .civil(CIVIL)
                .genero(GENERO)
                .endereco(ENDERECO)
                .complemento(COMPLEMENTO)
                .bairro(BAIRRO)
                .cidade(CIDADE)
                .estado(ESTADO)
                .telefone(TELEFONE)
                .celular(CELULAR)
                .email(EMAIL)
                .build();
    }
}
