package br.com.mascenadev.vollmed.repository;

import br.com.mascenadev.vollmed.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByAtivoTrue(Pageable pageable);
}
