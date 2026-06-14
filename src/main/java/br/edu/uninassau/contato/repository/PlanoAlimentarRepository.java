package br.edu.uninassau.contato.repository;

import br.edu.uninassau.contato.entity.PlanoAlimentar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanoAlimentarRepository extends JpaRepository<PlanoAlimentar, Long> {

    List<PlanoAlimentar> findByUsuarioIdUser(Long idUser);

    List<PlanoAlimentar> findByNutricionistaIdNutri(Long idNutri);

}