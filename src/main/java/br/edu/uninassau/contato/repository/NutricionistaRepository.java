package br.edu.uninassau.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.contato.entity.Nutricionista;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

}