package br.com.mascenadev.vollmed.dto;

import br.com.mascenadev.vollmed.entities.enums.Specialties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDoctorsDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Specialties especialidade,

        @NotNull
        @Valid
        DataAddressDTO endereco) {
}