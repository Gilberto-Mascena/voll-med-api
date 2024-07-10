package br.com.mascenadev.vollmed.repository;

import br.com.mascenadev.vollmed.entities.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, Long> {
}
