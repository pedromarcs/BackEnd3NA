package br.edu.uninassau.contato.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "vinculo_nutri_paciente")
public class VinculoNutriPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vinculo")
    private Long idVinculo;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_user", nullable = false)
    private Usuario usuario;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_nutri", nullable = false)
    private Nutricionista nutricionista;
    @Column(name = "fk_id_solicitacao")
    private Long fkIdSolicitacao;  // ✅ CORRIGIDO: Era LocalDate, agora é Long
    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao;
    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;
    @Column(name = "status", length = 20)
    private String status = "Pendente";
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    public VinculoNutriPaciente() {}
    public Long getIdVinculo() { return idVinculo; }
    public void setIdVinculo(Long idVinculo) { this.idVinculo = idVinculo; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }
    
    public Long getFkIdSolicitacao() { return fkIdSolicitacao; }
    public void setFkIdSolicitacao(Long fkIdSolicitacao) { this.fkIdSolicitacao = fkIdSolicitacao; }  // ✅ CORRIGIDO: Agora recebe Long
    
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    
    public LocalDate getDataAprovacao() { return dataAprovacao; }
    public void setDataAprovacao(LocalDate dataAprovacao) { this.dataAprovacao = dataAprovacao; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}