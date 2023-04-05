package dev.matheus.projeto.api.emprestimo.controller;

import dev.matheus.projeto.api.emprestimo.entity.Cliente;
import dev.matheus.projeto.api.emprestimo.entity.Emprestimo;
import dev.matheus.projeto.api.emprestimo.repository.EmprestimoRepository;
import dev.matheus.projeto.api.emprestimo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmprestimoController {

    @Autowired
    EmprestimoRepository repository;
    @Autowired
    ClienteService service;

    @RequestMapping(method = RequestMethod.POST, value = "/clientes/{cpf}/emprestimos")
    public void cadastrarEmprestimo(@RequestBody @Valid Emprestimo emprestimo, @PathVariable String cpf) {
        if(service.buscarByCpf(cpf) != null){
            if(verificaRendimento)
            repository.save(emprestimo);
        }
    }

}
