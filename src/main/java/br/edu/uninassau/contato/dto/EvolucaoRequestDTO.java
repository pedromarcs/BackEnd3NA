package br.edu.uninassau.contato.dto;

import java.math.BigDecimal;

public class EvolucaoRequestDTO {

    private Long fkIdUser;
    private String dataRegistro;
    private BigDecimal pesoRegistrado;
    private BigDecimal metaProgresso;

    public Long getFkIdUser() { return fkIdUser; }
    public void setFkIdUser(Long fkIdUser) { this.fkIdUser = fkIdUser; }

    public String getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public BigDecimal getPesoRegistrado() { return pesoRegistrado; }
    public void setPesoRegistrado(BigDecimal pesoRegistrado) { this.pesoRegistrado = pesoRegistrado; }

    public BigDecimal getMetaProgresso() { return metaProgresso; }
    public void setMetaProgresso(BigDecimal metaProgresso) { this.metaProgresso = metaProgresso; }
}