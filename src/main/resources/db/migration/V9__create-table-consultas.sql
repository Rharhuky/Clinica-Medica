CREATE TABLE consultas(
    id SERIAL,
    medico_id INTEGER NOT NULL,
    paciente_id INTEGER NOT NULL,
    data TIMESTAMP NOT NULL,
    motivo_cancelamento VARCHAR(20),

    CONSTRAINT pk_consultas_id PRIMARY KEY(id),
    CONSTRAINT fk_consultas_medico_id FOREIGN KEY(medico_id) REFERENCES medicos(medico_id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY(paciente_id) REFERENCES pacientes(id)

);