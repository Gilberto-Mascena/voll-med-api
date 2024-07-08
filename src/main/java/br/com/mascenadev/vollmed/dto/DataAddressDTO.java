package br.com.mascenadev.vollmed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAddressDTO(
        @NotBlank(message = "{endereco.obrigatorio}")
        String logradouro,

        @NotBlank(message = "{endereco.obrigatorio}")
        String bairro,

        @NotBlank(message = "{endereco.obrigatorio}")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "{endereco.invalido}")
        String cep,

        @NotBlank(message = "{endereco.obrigatorio}")
        String cidade,

        @NotBlank(message = "{endereco.obrigatorio}")
        String uf,

        String complemento,
        String numero) {
}