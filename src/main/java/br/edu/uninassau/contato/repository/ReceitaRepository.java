package br.edu.uninassau.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.contato.entity.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}