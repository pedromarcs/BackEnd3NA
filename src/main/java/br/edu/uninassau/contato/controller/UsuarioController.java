package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.Usuario;
import br.edu.uninassau.contato.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body("Usuário criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putUsuario(@PathVariable Long id, @RequestBody Usuario dados) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNomeCompleto(dados.getNomeCompleto());
            u.setEmail(dados.getEmail());
            u.setObjetivoSaude(dados.getObjetivoSaude());
            u.setTipoDieta(dados.getTipoDieta());
            u.setRestricoesReligiosas(dados.getRestricoesReligiosas());
            u.setAlergias(dados.getAlergias());
            u.setRotinaAtividade(dados.getRotinaAtividade());
            u.setPesoAtual(dados.getPesoAtual());
            u.setPesoMeta(dados.getPesoMeta());
            u.setAltura(dados.getAltura());
            u.setStatus(dados.getStatus());
            usuarioRepository.save(u);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}