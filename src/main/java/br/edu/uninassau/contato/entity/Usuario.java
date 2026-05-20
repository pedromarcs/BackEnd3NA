package br.edu.uninassau.contato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
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

    // ===== RELACIONAMENTOS =====
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evolucao> evolucoes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VinculoNutriPaciente> vinculos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitacoesDeContratacao> solicitacoes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanoAlimentar> planos = new ArrayList<>();

    public Usuario() {}

    // ===== GETTERS E SETTERS BÁSICOS =====
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

    // ===== GETTERS E SETTERS RELACIONAMENTOS =====
    public List<Evolucao> getEvolucoes() { return evolucoes; }
    public void setEvolucoes(List<Evolucao> evolucoes) { this.evolucoes = evolucoes; }
    public List<VinculoNutriPaciente> getVinculos() { return vinculos; }
    public void setVinculos(List<VinculoNutriPaciente> vinculos) { this.vinculos = vinculos; }
    public List<SolicitacoesDeContratacao> getSolicitacoes() { return solicitacoes; }
    public void setSolicitacoes(List<SolicitacoesDeContratacao> solicitacoes) { this.solicitacoes = solicitacoes; }
    public List<PlanoAlimentar> getPlanos() { return planos; }
    public void setPlanos(List<PlanoAlimentar> planos) { this.planos = planos; }
}package br.edu.uninassau.contato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
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

    // ===== RELACIONAMENTOS =====
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evolucao> evolucoes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VinculoNutriPaciente> vinculos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitacoesDeContratacao> solicitacoes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanoAlimentar> planos = new ArrayList<>();

    public Usuario() {}

    // ===== GETTERS E SETTERS BÁSICOS =====
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

    // ===== GETTERS E SETTERS RELACIONAMENTOS =====
    public List<Evolucao> getEvolucoes() { return evolucoes; }
    public void setEvolucoes(List<Evolucao> evolucoes) { this.evolucoes = evolucoes; }
    public List<VinculoNutriPaciente> getVinculos() { return vinculos; }
    public void setVinculos(List<VinculoNutriPaciente> vinculos) { this.vinculos = vinculos; }
    public List<SolicitacoesDeContratacao> getSolicitacoes() { return solicitacoes; }
    public void setSolicitacoes(List<SolicitacoesDeContratacao> solicitacoes) { this.solicitacoes = solicitacoes; }
    public List<PlanoAlimentar> getPlanos() { return planos; }
    public void setPlanos(List<PlanoAlimentar> planos) { this.planos = planos; }
}