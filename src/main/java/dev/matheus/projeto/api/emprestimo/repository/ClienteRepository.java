package dev.matheus.projeto.api.emprestimo.repository;

import dev.matheus.projeto.api.emprestimo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
