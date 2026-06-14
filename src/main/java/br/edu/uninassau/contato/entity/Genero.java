package br.edu.uninassau.contato.entity;

public enum Genero { 

    MASCULINO("Masculino"), 

    FEMININO("Feminino"), 

    OUTRO("Outro"), 

    NAO_INFORMADO("Não informado"); 

  

    private final String descricao; 

  

    Genero(String descricao) { 

        this.descricao = descricao; 

    } 

  

    public String getDescricao() { 

        return descricao; 

    } 

}