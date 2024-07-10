package br.com.mascenadev.vollmed.dto;

import br.com.mascenadev.vollmed.entities.Address;
import br.com.mascenadev.vollmed.entities.Doctor;
import br.com.mascenadev.vollmed.entities.enums.Specialties;

public record DataDetailsDoctorsDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Specialties especialidade,
        Address endereco) {

    public DataDetailsDoctorsDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getNome(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getTelefone(),
                doctor.getEspecialidade(),
                doctor.getEndereco());
    }
}