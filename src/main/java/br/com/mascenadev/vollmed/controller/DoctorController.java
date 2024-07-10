package br.com.mascenadev.vollmed.controller;

import br.com.mascenadev.vollmed.dto.doctor.DataDetailsDoctorsDTO;
import br.com.mascenadev.vollmed.dto.doctor.RegisterDoctorsDTO;
import br.com.mascenadev.vollmed.dto.doctor.UpdateDoctorsDTO;
import br.com.mascenadev.vollmed.dto.doctor.ListDoctorsDTO;
import br.com.mascenadev.vollmed.entities.Doctor;
import br.com.mascenadev.vollmed.repository.DoctorRepository;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    @Resource
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerDoctor(@RequestBody @Valid RegisterDoctorsDTO data, UriComponentsBuilder uriComponentsBuilder) {

        var doctor = new Doctor(data);
        doctorRepository.save(doctor);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsDoctorsDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorsDTO>> listDoctors(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        var page = doctorRepository.findAllByAtivoTrue(pageable)
                .map(ListDoctorsDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid UpdateDoctorsDTO data) {
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateDoctor(data);
        return ResponseEntity.ok(new DataDetailsDoctorsDTO(doctor));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailsDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailsDoctorsDTO(doctor));
    }
}