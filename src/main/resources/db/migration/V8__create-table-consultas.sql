create table pacientes(
    id SERIAL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(60) NOT NULL,
    cpf VARCHAR(14) NOT NULL ,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(11) NOT NULL,
    numero VARCHAR(20),
    cidade VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL,

-- CONSTRAINTS

   CONSTRAINT pk_pacientes_id PRIMARY KEY(id),
   CONSTRAINT uk_pacientes_cpf UNIQUE(cpf),
   CONSTRAINT uk_pacientes_email UNIQUE(email)

);
