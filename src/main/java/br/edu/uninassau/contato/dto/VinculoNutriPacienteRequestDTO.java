package br.edu.uninassau.contato.dto;

import java.time.LocalDate; 



public class VinculoNutriPacienteRequestDTO { 

    private Long usuarioId; 

    private Long nutricionistaId; 

    private LocalDate dataSolicitacao; 

    private String status; 

  

    public Long getUsuarioId() { return usuarioId; } 

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; } 

    public Long getNutricionistaId() { return nutricionistaId; } 

    public void setNutricionistaId(Long nutricionistaId) { this.nutricionistaId = nutricionistaId; } 

    public LocalDate getDataSolicitacao() { return dataSolicitacao; } 

    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; } 

    public String getStatus() { return status; } 

    public void setStatus(String status) { this.status = status; } 

} 