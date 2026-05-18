package br.edu.uninassau.contato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plano_alimentar")
public class PlanoAlimentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Long idPlano;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_user", nullable = false)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_nutri_criador", nullable = false)
    private Nutricionista nutricionista;

    @Column(name = "calorias_alvo", precision = 8, scale = 2)
    private BigDecimal caloriasAlvo;

    @Column(name = "proteina_alvo", precision = 6, scale = 2)
    private BigDecimal proteinaAlvo;

    @Column(name = "gordura_alvo", precision = 6, scale = 2)
    private BigDecimal gorduraAlvo;

    @Column(name = "carbo_alvo", precision = 6, scale = 2)
    private BigDecimal carboAlvo;

    @Column(name = "status", length = 20)
    private String status = "Ativo";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL)
    private Set<PlanoReceita> planoReceitas = new HashSet<>();

    public PlanoAlimentar() {}

    public Long getIdPlano() { return idPlano; }
    public void setIdPlano(Long idPlano) { this.idPlano = idPlano; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }
    public BigDecimal getCaloriasAlvo() { return caloriasAlvo; }
    public void setCaloriasAlvo(BigDecimal caloriasAlvo) { this.caloriasAlvo = caloriasAlvo; }
    public BigDecimal getProteinaAlvo() { return proteinaAlvo; }
    public void setProteinaAlvo(BigDecimal proteinaAlvo) { this.proteinaAlvo = proteinaAlvo; }
    public BigDecimal getGorduraAlvo() { return gorduraAlvo; }
    public void setGorduraAlvo(BigDecimal gorduraAlvo) { this.gorduraAlvo = gorduraAlvo; }
    public BigDecimal getCarboAlvo() { return carboAlvo; }
    public void setCarboAlvo(BigDecimal carboAlvo) { this.carboAlvo = carboAlvo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Set<PlanoReceita> getPlanoReceitas() { return planoReceitas; }
    public void setPlanoReceitas(Set<PlanoReceita> planoReceitas) { this.planoReceitas = planoReceitas; }
}