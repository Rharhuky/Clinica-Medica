

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


CREATE TABLE consultas(
    id SERIAL,
    medico_id INTEGER NOT NULL,
    paciente_id INTEGER NOT NULL,
    data TIMESTAMP NOT NULL,

    CONSTRAINT pk_consultas_id PRIMARY KEY(id),
    CONSTRAINT fk_consultas_medico_id FOREIGN KEY(medico_id) REFERENCES medicos(medico_id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY(paciente_id) REFERENCES pacientes(id)

);