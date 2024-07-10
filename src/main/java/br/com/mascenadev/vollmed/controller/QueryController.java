package br.com.mascenadev.vollmed.controller;

import br.com.mascenadev.vollmed.dto.query.DetailingConsultationDTO;
import br.com.mascenadev.vollmed.dto.query.SchedulingConsultationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class QueryController {

    @PostMapping
    @Transactional
    public ResponseEntity toSchedule(@RequestBody @Valid SchedulingConsultationDTO schedulingConsultation) {

        System.out.println("Scheduling consultation: " + schedulingConsultation);
        return ResponseEntity.ok(new DetailingConsultationDTO(null, null, null, null));
    }
}