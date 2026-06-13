package br.edu.uninassau.contato.dto;

import java.time.LocalDate;

public class PlanoReceitaRequestDTO {
    private Long idPlano;
    private Long idReceita;
    private LocalDate dataInclusao;

    public Long getIdPlano() { return idPlano; }
    public void setIdPlano(Long idPlano) { this.idPlano = idPlano; }

    public Long getIdReceita() { return idReceita; }
    public void setIdReceita(Long idReceita) { this.idReceita = idReceita; }

    public LocalDate getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDate dataInclusao) { this.dataInclusao = dataInclusao; }
}