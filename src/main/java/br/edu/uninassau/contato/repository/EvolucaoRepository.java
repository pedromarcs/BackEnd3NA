package br.edu.uninassau.contato.repository;

import br.edu.uninassau.contato.entity.Evolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {
    List<Evolucao> findByUsuarioIdUser(Long idUser);
}