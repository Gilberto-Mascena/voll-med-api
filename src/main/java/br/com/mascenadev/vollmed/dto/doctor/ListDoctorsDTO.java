package br.com.mascenadev.vollmed.dto;

import br.com.mascenadev.vollmed.entities.Doctor;
import br.com.mascenadev.vollmed.entities.enums.Specialties;

public record ListDoctorsDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Specialties especialidade) {

    public ListDoctorsDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getNome(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getEspecialidade());
    }
}