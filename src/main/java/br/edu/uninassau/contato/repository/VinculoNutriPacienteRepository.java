package br.edu.uninassau.contato.repository;

import br.edu.uninassau.contato.entity.VinculoNutriPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VinculoNutriPacienteRepository extends JpaRepository<VinculoNutriPaciente, Long> {
    List<VinculoNutriPaciente> findByUsuarioIdUser(Long idUser);
    List<VinculoNutriPaciente> findByNutricionistaIdNutri(Long idNutri);
}