package dev.matheus.projeto.api.emprestimo.controller;

import dev.matheus.projeto.api.emprestimo.entity.Cliente;
import dev.matheus.projeto.api.emprestimo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService service;

    @RequestMapping(method = RequestMethod.POST, value = "/clientes")
    public void cadastrarCliente(@RequestBody @Valid Cliente cliente) {
        service.cadastrarCliente(cliente);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clientes/{cpf}")
    public Cliente buscarByCpf(@PathVariable String cpf){
        return service.buscarByCpf(cpf);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clientes")
    public List<Cliente> buscarTodosClientes(){
        return service.buscarTodosClientes();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{cpf}")
    public void deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clientes/{cpf}")
    public void atualizarCliente(@RequestBody Cliente novosDadosCliente, @PathVariable String cpf){
        service.atualizarCliente(novosDadosCliente, cpf);
    }

}

