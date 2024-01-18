CREATE TYPE ramo AS ENUM ('CLINICO_GERAL','CARDIOLOGIA', 'ORTOPEDIA','ENDOCRINOLOGIA');

CREATE TABLE medicos(
    medico_id serial,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    cpf varchar(16) not null unique,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
--    complemento varchar(100),
    numero varchar(20),
--    uf char(2) not null,
   cidade varchar(100) not null,

    primary key(medico_id)

);