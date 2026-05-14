package br.edu.uninassau.contato.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "evolucao")
public class Evolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evolucao")
    private Long idEvolucao;

    @ManyToOne
    @JoinColumn(name = "fk_id_user", nullable = false)
    private Usuario usuario;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "peso_registrado", precision = 5, scale = 2)
    private BigDecimal pesoRegistrado;

    @Column(name = "meta_progresso", precision = 5, scale = 2)
    private BigDecimal metaProgresso;

    @Column(name = "total_calorias_consumidas", precision = 8, scale = 2)
    private BigDecimal totalCaloriasConsumidas;

    @Column(name = "refeicoes_concluidas")
    private Integer refeicoesConcluidasI = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Evolucao() {}

    public Long getIdEvolucao() { return idEvolucao; }
    public void setIdEvolucao(Long idEvolucao) { this.idEvolucao = idEvolucao; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }
    public BigDecimal getPesoRegistrado() { return pesoRegistrado; }
    public void setPesoRegistrado(BigDecimal pesoRegistrado) { this.pesoRegistrado = pesoRegistrado; }
    public BigDecimal getMetaProgresso() { return metaProgresso; }
    public void setMetaProgresso(BigDecimal metaProgresso) { this.metaProgresso = metaProgresso; }
    public BigDecimal getTotalCaloriasConsumidas() { return totalCaloriasConsumidas; }
    public void setTotalCaloriasConsumidas(BigDecimal totalCaloriasConsumidas) { this.totalCaloriasConsumidas = totalCaloriasConsumidas; }
    public Integer getRefeicoesConcluidasI() { return refeicoesConcluidasI; }
    public void setRefeicoesConcluidasI(Integer refeicoesConcluidasI) { this.refeicoesConcluidasI = refeicoesConcluidasI; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}