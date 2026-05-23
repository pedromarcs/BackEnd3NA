package br.edu.uninassau.contato.dto;

import java.time.LocalDate;

public class SolicitacoesDeContratacaoRequestDTO {
    private Long usuarioId;
    private Long nutricionistaId;
    private Long fkIdSolicitacao;
    private LocalDate dataSolicitacao;
    private String status;

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getNutricionistaId() { return nutricionistaId; }
    public void setNutricionistaId(Long nutricionistaId) { this.nutricionistaId = nutricionistaId; }
    public Long getFkIdSolicitacao() { return fkIdSolicitacao; }
    public void setFkIdSolicitacao(Long fkIdSolicitacao) { this.fkIdSolicitacao = fkIdSolicitacao; }
    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
	public Long getFkIdSolicitacao1() {
		return null;
	}
}