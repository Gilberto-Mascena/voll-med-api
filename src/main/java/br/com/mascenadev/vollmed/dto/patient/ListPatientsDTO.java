package br.com.mascenadev.vollmed.dto.patient;

import br.com.mascenadev.vollmed.entities.Patient;

public record ListPatientsDTO(
        Long id,
        String nome,
        String email,
        String cpf) {

    public ListPatientsDTO(Patient patient) {
        this(
                patient.getId(),
                patient.getNome(),
                patient.getEmail(),
                patient.getCpf());
    }
}