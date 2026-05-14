package br.edu.uninassau.contato.repository;

import br.edu.uninassau.contato.entity.SolicitacoesDeContratacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacoesDeContratacaoRepository extends JpaRepository<SolicitacoesDeContratacao, Long> {
    List<SolicitacoesDeContratacao> findByUsuarioIdUser(Long idUser);
    List<SolicitacoesDeContratacao> findByNutricionistaIdNutri(Long idNutri);
}