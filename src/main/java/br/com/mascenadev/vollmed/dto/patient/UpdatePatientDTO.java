package br.com.mascenadev.vollmed.dto.patient;

import br.com.mascenadev.vollmed.dto.address.DataAddressDTO;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddressDTO endereco) {
}