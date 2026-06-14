package br.edu.uninassau.contato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; 

import jakarta.persistence.*; 

import java.math.BigDecimal; 

import java.time.LocalDateTime; 

import java.util.HashSet; 

import java.util.Set; 

  

@Entity 

@Table(name = "receita") 

public class Receita { 

  

    @Id 

    @GeneratedValue(strategy = GenerationType.IDENTITY) 

    @Column(name = "id_receita") 

    private Long idReceita; 

  

    @JsonIgnore 

    @ManyToOne 

    @JoinColumn(name = "fk_id_nutri", nullable = false) 

    private Nutricionista nutricionista; 

  

    @Column(name = "titulo", nullable = false, length = 200) 

    private String titulo; 

  

    @Column(name = "ingredientes", nullable = false, columnDefinition = "TEXT") 

    private String ingredientes; 

  

    @Column(name = "modo_preparo", nullable = false, columnDefinition = "TEXT") 

    private String modoPreparo; 

  

    @Column(name = "calorias", precision = 8, scale = 2) 

    private BigDecimal calorias; 

  

    @Column(name = "proteinas", precision = 6, scale = 2) 

    private BigDecimal proteinas; 

  

    @Column(name = "carbos", precision = 6, scale = 2) 

    private BigDecimal carbos; 

  

    @Column(name = "gorduras", precision = 6, scale = 2) 

    private BigDecimal gorduras; 

  

    @Column(name = "tempo_preparo_minutos") 

    private Integer tempoPreparo; 

  

    @Column(name = "tags", length = 200) 

    private String tags; 

  

    @Column(name = "observacoes_nutri", columnDefinition = "TEXT") 

    private String observacoes; 

  

    @Column(name = "imagem_url", length = 200) 

    private String imagemUrl; 

  

    @Column(name = "created_at") 

    private LocalDateTime createdAt; 

  

    @JsonIgnore 

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL) 

    private Set<PlanoReceita> planoReceitas = new HashSet<>(); 

  

    public Receita() {} 

  

    public Long getIdReceita() { return idReceita; } 

    public void setIdReceita(Long idReceita) { this.idReceita = idReceita; } 

    public Nutricionista getNutricionista() { return nutricionista; } 

    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; } 

    public String getTitulo() { return titulo; } 

    public void setTitulo(String titulo) { this.titulo = titulo; } 

    public String getIngredientes() { return ingredientes; } 

    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; } 

    public String getModoPreparo() { return modoPreparo; } 

    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; } 

    public BigDecimal getCalorias() { return calorias; } 

    public void setCalorias(BigDecimal calorias) { this.calorias = calorias; } 

    public BigDecimal getProteinas() { return proteinas; } 

    public void setProteinas(BigDecimal proteinas) { this.proteinas = proteinas; } 

    public BigDecimal getCarbos() { return carbos; } 

    public void setCarbos(BigDecimal carbos) { this.carbos = carbos; } 

    public BigDecimal getGorduras() { return gorduras; } 

    public void setGorduras(BigDecimal gorduras) { this.gorduras = gorduras; } 

    public Integer getTempoPreparo() { return tempoPreparo; } 

    public void setTempoPreparo(Integer tempoPreparo) { this.tempoPreparo = tempoPreparo; } 

    public String getTags() { return tags; } 

    public void setTags(String tags) { this.tags = tags; } 

    public String getObservacoes() { return observacoes; } 

    public void setObservacoes(String observacoes) { this.observacoes = observacoes; } 

    public String getImagemUrl() { return imagemUrl; } 

    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; } 

    public LocalDateTime getCreatedAt() { return createdAt; } 

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; } 

    public Set<PlanoReceita> getPlanoReceitas() { return planoReceitas; } 

    public void setPlanoReceitas(Set<PlanoReceita> planoReceitas) { this.planoReceitas = planoReceitas; } 

}