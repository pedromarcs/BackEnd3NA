package br.edu.uninassau.contato.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "peso_inicial", precision = 5, scale = 2)
    private BigDecimal pesoInicial;

    @Column(name = "peso_atual", precision = 5, scale = 2)
    private BigDecimal pesoAtual;

    @Column(name = "peso_meta", precision = 5, scale = 2)
    private BigDecimal pesoMeta;

    @Column(name = "altura", precision = 4, scale = 2)
    private BigDecimal altura;

    @Column(name = "objetivo_saude", length = 100)
    private String objetivoSaude;

    @Column(name = "tipo_dieta", length = 100)
    private String tipoDieta;

    @Column(name = "restricoes_religiosas", length = 100)
    private String restricoesReligiosas;

    @Column(name = "alergias", columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "rotina_atividade", length = 100)
    private String rotinaAtividade;

    @Column(name = "status", length = 20)
    private String status = "Ativo";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Usuario() {}

    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public BigDecimal getPesoInicial() { return pesoInicial; }
    public void setPesoInicial(BigDecimal pesoInicial) { this.pesoInicial = pesoInicial; }
    public BigDecimal getPesoAtual() { return pesoAtual; }
    public void setPesoAtual(BigDecimal pesoAtual) { this.pesoAtual = pesoAtual; }
    public BigDecimal getPesoMeta() { return pesoMeta; }
    public void setPesoMeta(BigDecimal pesoMeta) { this.pesoMeta = pesoMeta; }
    public BigDecimal getAltura() { return altura; }
    public void setAltura(BigDecimal altura) { this.altura = altura; }
    public String getObjetivoSaude() { return objetivoSaude; }
    public void setObjetivoSaude(String objetivoSaude) { this.objetivoSaude = objetivoSaude; }
    public String getTipoDieta() { return tipoDieta; }
    public void setTipoDieta(String tipoDieta) { this.tipoDieta = tipoDieta; }
    public String getRestricoesReligiosas() { return restricoesReligiosas; }
    public void setRestricoesReligiosas(String restricoesReligiosas) { this.restricoesReligiosas = restricoesReligiosas; }
    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }
    public String getRotinaAtividade() { return rotinaAtividade; }
    public void setRotinaAtividade(String rotinaAtividade) { this.rotinaAtividade = rotinaAtividade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}