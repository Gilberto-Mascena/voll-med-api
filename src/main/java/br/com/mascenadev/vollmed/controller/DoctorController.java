package br.com.mascenadev.vollmed.controller;

import br.com.mascenadev.vollmed.dto.RegisterDoctorsDTO;
import br.com.mascenadev.vollmed.dto.UpdateDoctorsDTO;
import br.com.mascenadev.vollmed.dto.ListDoctors;
import br.com.mascenadev.vollmed.entities.Doctor;
import br.com.mascenadev.vollmed.repository.DoctorRepository;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    @Resource
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid RegisterDoctorsDTO data) {
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public Page<ListDoctors> listDoctors(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        return doctorRepository.findAllByAtivoTrue(pageable)
                    .map(ListDoctors::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorsDTO data) {
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateDoctor(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}