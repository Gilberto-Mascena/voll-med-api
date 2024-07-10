CREATE TABLE consulta
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    medico_id   BIGINT   NOT NULL,
    paciente_id BIGINT   NOT NULL,
    data        DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_consultas_medico_id FOREIGN KEY (medico_id) REFERENCES medicos (id),
    CONSTRAINT FK_consultas_paciente_id FOREIGN KEY (paciente_id) REFERENCES pacientes (id)
);