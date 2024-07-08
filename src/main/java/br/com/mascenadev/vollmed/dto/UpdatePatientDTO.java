package br.com.mascenadev.vollmed.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddressDTO endereco) {
}