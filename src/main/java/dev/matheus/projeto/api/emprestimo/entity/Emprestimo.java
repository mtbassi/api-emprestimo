package dev.matheus.projeto.api.emprestimo.entity;

import dev.matheus.projeto.api.emprestimo.enums.Relacionamento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Emprestimo(){

    }

    public Emprestimo(String cpfCliente, BigDecimal valorInicial, String relacionamento, LocalDate dataFinal){
        this.cpfCliente = cpfCliente;
        this.valorInicial = valorInicial;
        this.relacionamento = Relacionamento.valueOf(relacionamento);
        this.dataInicial = LocalDate.now();
        this.dataFinal = dataFinal;
    }

}
