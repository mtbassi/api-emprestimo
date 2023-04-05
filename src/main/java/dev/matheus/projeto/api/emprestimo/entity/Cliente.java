package dev.matheus.projeto.api.emprestimo.entity;

import dev.matheus.projeto.api.emprestimo.record.Endereco;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity //Anotacao determina que vai ser criado uma tabela no banco de dados
@Table(name = "TBL_CLIENTE")
public class Cliente {

    @Id
    @NotBlank(message = "CPF NULL/VAZIO")
    @Pattern(regexp = "[0-9]{11}", message = "CPF INVALIDO")
    private String cpf;
    @NotBlank(message = "NOME NULL/VAZIO")
    private String nome;
    @NotBlank(message = "TELEFONE NULL/VAZIO")
    private String telefone;
    @Embedded
    @NotNull(message = "ENDERECO NULL")
    private Endereco endereco;
    @NotNull(message = "RENDIMENTO MENSAL NULL")
    private BigDecimal rendimentoMensal;
    @Transient
    @OneToMany(mappedBy = "TBL_CLIENTE")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(String cpf, String nome, String telefone, Endereco endereco, BigDecimal rendimentoMensal) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = new Endereco(endereco);
        this.rendimentoMensal = rendimentoMensal;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(BigDecimal rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public static Cliente validaDados(Cliente cliente, Cliente novosDadosCliente) {
        if (novosDadosCliente.getNome() == null || novosDadosCliente.getNome().isEmpty()) {
            novosDadosCliente.setNome(cliente.getNome());
        }
        if (novosDadosCliente.getTelefone() == null || novosDadosCliente.getTelefone().isEmpty()) {
            novosDadosCliente.setTelefone(cliente.getTelefone());
        }
        if (novosDadosCliente.getEndereco() == null) {
            novosDadosCliente.setEndereco(cliente.getEndereco());
        }
        if (novosDadosCliente.getRendimentoMensal() == null) {
            novosDadosCliente.setRendimentoMensal(cliente.getRendimentoMensal());
        }
        return novosDadosCliente;
    }
}
