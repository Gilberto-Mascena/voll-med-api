package br.com.mascenadev.vollmed.dto.doctor;

import br.com.mascenadev.vollmed.dto.address.DataAddressDTO;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorsDTO(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddressDTO endereco) {
}