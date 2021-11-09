CREATE TABLE public.pessoa
(
    oid_pessoa serial NOT NULL,
    nom_pessoa character varying(200) NOT NULL,
    nro_documento character varying(50) NOT NULL,
    nom_mae character varying(200),
    nom_pai character varying(200),
    dat_nascimento date,
    tpo_civil character varying(50),
    tpo_genero character varying(50),
    des_endereco character varying(200),
    des_complemento character varying(200),
    nom_bairro character varying(200),
    nom_cidade character varying(200),
    cod_estado character varying(5),
    nro_telefone character varying(50),
    nro_celular character varying(50),
    des_email character varying(200),
    PRIMARY KEY (oid_pessoa),
    UNIQUE (nro_documento)
);

ALTER TABLE IF EXISTS public.pessoa
    OWNER to "postgres";