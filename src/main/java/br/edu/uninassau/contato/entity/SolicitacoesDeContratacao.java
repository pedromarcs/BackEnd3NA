package br.edu.uninassau.contato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes_de_contratacao")
public class SolicitacoesDeContratacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_user", nullable = false)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_nutri", nullable = false)
    private Nutricionista nutricionista;

    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao;

    @Column(name = "status", length = 20)
    private String status = "Pendente";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public SolicitacoesDeContratacao() {}

    public Long getIdSolicitacao() { return idSolicitacao; }
    public void setIdSolicitacao(Long idSolicitacao) { this.idSolicitacao = idSolicitacao; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}