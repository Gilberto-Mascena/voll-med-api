package br.com.mascenadev.vollmed.dto;

import java.time.LocalDateTime;

public record DetailingConsultationDTO(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date) {
}