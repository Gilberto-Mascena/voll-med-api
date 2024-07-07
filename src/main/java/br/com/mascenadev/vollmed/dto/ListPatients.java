package br.com.mascenadev.vollmed.dto;

import br.com.mascenadev.vollmed.entities.Patient;

public record ListPatients(
        Long id,
        String nome,
        String email,
        String cpf) {

    public ListPatients(Patient patient) {
        this(
                patient.getId(),
                patient.getNome(),
                patient.getEmail(),
                patient.getCpf());
    }
}