package br.edu.uninassau.contato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senhaHash;

    private String objetivoSaude;
    private String tipoDieta;
    private String alergias;
    private String rotina;
    private String status = "Ativo";

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public String getObjetivoSaude() { return objetivoSaude; }
    public void setObjetivoSaude(String objetivoSaude) { this.objetivoSaude = objetivoSaude; }
    public String getTipoDieta() { return tipoDieta; }
    public void setTipoDieta(String tipoDieta) { this.tipoDieta = tipoDieta; }
    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }
    public String getRotina() { return rotina; }
    public void setRotina(String rotina) { this.rotina = rotina; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}