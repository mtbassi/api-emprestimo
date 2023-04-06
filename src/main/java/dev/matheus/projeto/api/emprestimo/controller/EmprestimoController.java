package dev.matheus.projeto.api.emprestimo.controller;

import dev.matheus.projeto.api.emprestimo.entity.Cliente;
import dev.matheus.projeto.api.emprestimo.entity.Emprestimo;
import dev.matheus.projeto.api.emprestimo.enums.Relacionamento;
import dev.matheus.projeto.api.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;
    @Autowired
    private ClienteController clienteController;

    @RequestMapping(method = RequestMethod.POST, value = "/clientes/{cpf}/emprestimos")
    public void cadastrarEmprestimo(@RequestBody @Valid Emprestimo emprestimo, @PathVariable String cpf) {
        Cliente cliente = clienteController.buscarByCpf(cpf);

        if (cliente != null) {

            // Set cpf do cliente no emprestimo
            emprestimo.setCpfCliente(cpf);

            // Calcula valor final do emprestimo
            List<Emprestimo> emprestimos = service.buscarEmprestimosByCpf(cpf);
            Relacionamento relacionamento = emprestimo.getRelacionamento();
            BigDecimal valorFinal = relacionamento.calculaValorFinal(emprestimo.getValorInicial(), emprestimos);
            emprestimo.setValorFinal(valorFinal);

            // Adicionar o novo emprestimo a lista de emprestimo do cliente
            emprestimos.add(emprestimo);
            cliente.setEmprestimos(emprestimos);

            //Calcula o valor total de todos os emprestimos
            BigDecimal total = BigDecimal.ZERO;
            for (Emprestimo emp : emprestimos) {
                total = total.add(emp.getValorFinal());
            }

            //Valida se o cliente pode abrir emprestimo de acordo com o seu rendimento mensal
            BigDecimal rendimentoMensal = cliente.getRendimentoMensal();
            if (rendimentoMensal.multiply(BigDecimal.TEN).compareTo(total) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "VALOR TOTAL DOS EMPRESTIMOS 10X MAIOR QUE O RENDIMENTO MENSAL DO CLIENTE");
            } else {
                service.cadastrarEmprestimo(emprestimo);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CLIENTE NAO ENCONTRADO");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clientes/{cpf}/emprestimos")
    public List<Emprestimo> buscarEmprestimosByCpf(@PathVariable String cpf){
        return service.buscarEmprestimosByCpf(cpf);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clientes/{cpf}/emprestimos/{id}")
    public List<Emprestimo> buscarEmprestimosById(@PathVariable String cpf, @PathVariable Integer id){
        return service.buscarEmprestimosById(cpf, id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{cpf}/emprestimos/{id}")
    public void deletarEmprestimosById(@PathVariable String cpf, @PathVariable Integer id){
        service.deletarEmprestimosById(cpf, id);
    }


}
