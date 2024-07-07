package br.com.mascenadev.vollmed.dto;

import br.com.mascenadev.vollmed.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco) {

}