package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import br.edu.uninassau.contato.entity.Usuario;
import br.edu.uninassau.contato.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body("Usuário criado com sucesso!");
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<String> putUsuario(@PathVariable Long id, @RequestBody Usuario dados) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNome(dados.getNome());
            u.setEmail(dados.getEmail());
            u.setObjetivoSaude(dados.getObjetivoSaude());
            u.setTipoDieta(dados.getTipoDieta());
            u.setAlergias(dados.getAlergias());
            u.setRotina(dados.getRotina());
            u.setStatus(dados.getStatus());
            usuarioRepository.save(u);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}