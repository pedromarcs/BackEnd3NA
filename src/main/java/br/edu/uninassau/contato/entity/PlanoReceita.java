package br.edu.uninassau.contato.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "plano_receita")
public class PlanoReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano_receita")
    private Long idPlanoReceita;

    @ManyToOne
    @JoinColumn(name = "fk_id_plano", nullable = false)
    private PlanoAlimentar plano;

    @ManyToOne
    @JoinColumn(name = "fk_id_receita", nullable = false)
    private Receita receita;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDate dataInclusao;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PlanoReceita() {}

    public Long getIdPlanoReceita() { return idPlanoReceita; }
    public void setIdPlanoReceita(Long idPlanoReceita) { this.idPlanoReceita = idPlanoReceita; }
    public PlanoAlimentar getPlano() { return plano; }
    public void setPlano(PlanoAlimentar plano) { this.plano = plano; }
    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }
    public LocalDate getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDate dataInclusao) { this.dataInclusao = dataInclusao; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}