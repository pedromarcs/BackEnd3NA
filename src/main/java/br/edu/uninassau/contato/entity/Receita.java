package br.edu.uninassau.contato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String ingredientes;

    @Column(nullable = false)
    private String modoPreparo;

    private Double calorias;
    private Double proteinas;
    private Double carbos;
    private Double gorduras;
    private Integer tempoPreparo;
    private String tags;
    private String observacoes;
    private String imagemUrl;

    public Receita() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    public String getModoPreparo() { return modoPreparo; }
    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }
    public Double getCalorias() { return calorias; }
    public void setCalorias(Double calorias) { this.calorias = calorias; }
    public Double getProteinas() { return proteinas; }
    public void setProteinas(Double proteinas) { this.proteinas = proteinas; }
    public Double getCarbos() { return carbos; }
    public void setCarbos(Double carbos) { this.carbos = carbos; }
    public Double getGorduras() { return gorduras; }
    public void setGorduras(Double gorduras) { this.gorduras = gorduras; }
    public Integer getTempoPreparo() { return tempoPreparo; }
    public void setTempoPreparo(Integer tempoPreparo) { this.tempoPreparo = tempoPreparo; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}