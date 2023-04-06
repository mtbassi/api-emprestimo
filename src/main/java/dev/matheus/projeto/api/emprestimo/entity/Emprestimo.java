package dev.matheus.projeto.api.emprestimo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.matheus.projeto.api.emprestimo.enums.Relacionamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "TBL_EMPRESTIMO")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpfCliente;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    @Enumerated(EnumType.STRING)
    private Relacionamento relacionamento;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataInicial;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataFinal;

    public Emprestimo(){
        this.dataInicial = LocalDate.now();
    }

    public Emprestimo(BigDecimal valorInicial, String relacionamento, LocalDate dataFinal){
        this.valorInicial = valorInicial;
        this.relacionamento = Relacionamento.valueOf(relacionamento);
        this.dataFinal = dataFinal;
        this.dataInicial = LocalDate.now();
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }
    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public static String localDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

}
