package br.edu.uninassau.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.contato.entity.Nutricionista;
import java.util.Optional;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Optional<Nutricionista> findByEmailProfissional(String emailProfissional);
}