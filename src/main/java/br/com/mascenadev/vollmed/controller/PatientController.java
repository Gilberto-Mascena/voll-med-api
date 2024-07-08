package br.com.mascenadev.vollmed.controller;

import br.com.mascenadev.vollmed.dto.ListPatientsDTO;
import br.com.mascenadev.vollmed.dto.RegisterPatientDTO;
import br.com.mascenadev.vollmed.dto.UpdatePatientDTO;
import br.com.mascenadev.vollmed.entities.Patient;
import br.com.mascenadev.vollmed.repository.PatientRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    @Resource
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void registerPatient(@RequestBody @Valid RegisterPatientDTO dados) {
        patientRepository.save(new Patient(dados));
    }

    @GetMapping
    public Page<ListPatientsDTO> listPatients(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return patientRepository.findAllByAtivoTrue(pageable).map(ListPatientsDTO::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid UpdatePatientDTO dados) {
        var patient = patientRepository.getReferenceById(dados.id());
        patient.updatePatient(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.deletePatien();
    }
}