package br.edu.uninassau.contato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plano_alimentar")
public class PlanoAlimentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_nutricionista", nullable = false)
    private Nutricionista nutricionista;

    private Double caloriasAlvo;
    private Double proteinaAlvo;
    private Double gorduraAlvo;
    private Double carboAlvo;
    private String status = "Ativo";

    public PlanoAlimentar() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }
    public Double getCaloriasAlvo() { return caloriasAlvo; }
    public void setCaloriasAlvo(Double caloriasAlvo) { this.caloriasAlvo = caloriasAlvo; }
    public Double getProteinaAlvo() { return proteinaAlvo; }
    public void setProteinaAlvo(Double proteinaAlvo) { this.proteinaAlvo = proteinaAlvo; }
    public Double getGorduraAlvo() { return gorduraAlvo; }
    public void setGorduraAlvo(Double gorduraAlvo) { this.gorduraAlvo = gorduraAlvo; }
    public Double getCarboAlvo() { return carboAlvo; }
    public void setCarboAlvo(Double carboAlvo) { this.carboAlvo = carboAlvo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}