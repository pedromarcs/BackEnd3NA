package br.edu.uninassau.contato.repository;

import br.edu.uninassau.contato.entity.PlanoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanoReceitaRepository extends JpaRepository<PlanoReceita, Long> {

    List<PlanoReceita> findByPlanoIdPlano(Long idPlano);

}