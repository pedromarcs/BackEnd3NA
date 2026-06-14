package br.edu.uninassau.contato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; 

import jakarta.persistence.*; 

import java.math.BigDecimal; 

import java.time.LocalDateTime; 

import java.util.ArrayList; 

import java.util.List; 

  

@Entity 

@Table(name = "nutricionista") 

public class Nutricionista { 

    @Id 

    @GeneratedValue(strategy = GenerationType.IDENTITY) 

    @Column(name = "id_nutri") 

    private Long idNutri; 

    @Column(name = "nome_completo", nullable = false, length = 150) 

    private String nomeCompleto; 

    @Column(name = "email_profissional", nullable = false, unique = true, length = 150) 

    private String emailProfissional; 

    @Column(name = "senha_hash", nullable = false, length = 255) 

    private String senhaHash; 

    @Column(name = "crn", unique = true, length = 20) 

    private String crn; 

    @Column(name = "uf", length = 2) 

    private String uf; 

    @Column(name = "especialidade_principal", length = 100) 

    private String especialidadePrincipal; 

    @Column(name = "biografia", columnDefinition = "TEXT") 

    private String biografia; 

    @Column(name = "avaliacao_media", precision = 4, scale = 2) 

    private BigDecimal avaliacaoMedia = BigDecimal.ZERO; 

    @Column(name = "total_pacientes") 

    private Integer totalPacientes = 0; 

    @Column(name = "created_at") 

    private LocalDateTime createdAt; 

    @Column(name = "foto_url") 

    private String fotoUrl; 

  

    public String getFotoUrl() { return fotoUrl; } 

    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; } 

  

    @JsonIgnore 

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL, orphanRemoval = true) 

    private List<Receita> receitas = new ArrayList<>(); 

  

    @JsonIgnore 

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL, orphanRemoval = true) 

    private List<PlanoAlimentar> planos = new ArrayList<>(); 

  

    @JsonIgnore 

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL, orphanRemoval = true) 

    private List<VinculoNutriPaciente> vinculos = new ArrayList<>(); 

  

    @JsonIgnore 

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL, orphanRemoval = true) 

    private List<SolicitacoesDeContratacao> solicitacoes = new ArrayList<>(); 

  

    public Nutricionista() {} 

  

    public Long getIdNutri() { return idNutri; } 

    public void setIdNutri(Long idNutri) { this.idNutri = idNutri; } 

    public String getNomeCompleto() { return nomeCompleto; } 

    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; } 

    public String getEmailProfissional() { return emailProfissional; } 

    public void setEmailProfissional(String emailProfissional) { this.emailProfissional = emailProfissional; } 

    public String getSenhaHash() { return senhaHash; } 

    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; } 

    public String getCrn() { return crn; } 

    public void setCrn(String crn) { this.crn = crn; } 

    public String getUf() { return uf; } 

    public void setUf(String uf) { this.uf = uf; } 

    public String getEspecialidadePrincipal() { return especialidadePrincipal; } 

    public void setEspecialidadePrincipal(String especialidadePrincipal) { this.especialidadePrincipal = especialidadePrincipal; } 

    public String getBiografia() { return biografia; } 

    public void setBiografia(String biografia) { this.biografia = biografia; } 

    public BigDecimal getAvaliacaoMedia() { return avaliacaoMedia; } 

    public void setAvaliacaoMedia(BigDecimal avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; } 

    public Integer getTotalPacientes() { return totalPacientes; } 

    public void setTotalPacientes(Integer totalPacientes) { this.totalPacientes = totalPacientes; } 

    public LocalDateTime getCreatedAt() { return createdAt; } 

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; } 

    public List<Receita> getReceitas() { return receitas; } 

    public void setReceitas(List<Receita> receitas) { this.receitas = receitas; } 

    public List<PlanoAlimentar> getPlanos() { return planos; } 

    public void setPlanos(List<PlanoAlimentar> planos) { this.planos = planos; } 

    public List<VinculoNutriPaciente> getVinculos() { return vinculos; } 

    public void setVinculos(List<VinculoNutriPaciente> vinculos) { this.vinculos = vinculos; } 

    public List<SolicitacoesDeContratacao> getSolicitacoes() { return solicitacoes; } 

    public void setSolicitacoes(List<SolicitacoesDeContratacao> solicitacoes) { this.solicitacoes = solicitacoes; } 

}