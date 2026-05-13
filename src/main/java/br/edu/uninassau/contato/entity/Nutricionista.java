package br.edu.uninassau.contato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nutricionista")
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senhaHash;

    @Column(unique = true)
    private String crn;

    private String uf;
    private String especialidade;
    private String biografia;
    private Double avaliacaoMedia = 0.0;
    private Integer totalPacientes = 0;

    public Nutricionista() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public String getCrn() { return crn; }
    public void setCrn(String crn) { this.crn = crn; }
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getBiografia() { return biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }
    public Double getAvaliacaoMedia() { return avaliacaoMedia; }
    public void setAvaliacaoMedia(Double avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; }
    public Integer getTotalPacientes() { return totalPacientes; }
    public void setTotalPacientes(Integer totalPacientes) { this.totalPacientes = totalPacientes; }
}