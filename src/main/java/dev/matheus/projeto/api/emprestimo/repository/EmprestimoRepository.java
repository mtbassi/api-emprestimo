package dev.matheus.projeto.api.emprestimo.repository;

import dev.matheus.projeto.api.emprestimo.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
}
