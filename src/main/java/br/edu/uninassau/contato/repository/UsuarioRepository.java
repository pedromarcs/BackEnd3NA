package br.edu.uninassau.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.contato.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}