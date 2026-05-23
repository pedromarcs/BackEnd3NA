package br.edu.uninassau.contato.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.Usuario;
import br.edu.uninassau.contato.repository.UsuarioRepository;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<?> criarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(salvo);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario dados) {
        return usuarioRepository.findByEmail(dados.getEmail())
            .map(u -> {
                if (u.getSenhaHash().equals(dados.getSenhaHash())) {
                    return ResponseEntity.ok(u);
                }
                return ResponseEntity.status(401).body("Senha incorreta!");
            })
            .orElse(ResponseEntity.status(404).body("Usuário não encontrado!"));
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
    public ResponseEntity<?> putUsuario(@PathVariable Long id, @RequestBody Usuario dados) {
        return usuarioRepository.findById(id).map(u -> {
            // Atualiza todos os campos
            u.setNomeCompleto(dados.getNomeCompleto());
            u.setEmail(dados.getEmail());
            u.setSenhaHash(dados.getSenhaHash());
            u.setDataNascimento(dados.getDataNascimento());  // ✅ GÊNERO
            u.setGenero(dados.getGenero());                  // ✅ DATA DE NASCIMENTO
            u.setPesoInicial(dados.getPesoInicial());
            u.setPesoAtual(dados.getPesoAtual());
            u.setPesoMeta(dados.getPesoMeta());
            u.setAltura(dados.getAltura());
            u.setObjetivoSaude(dados.getObjetivoSaude());
            u.setTipoDieta(dados.getTipoDieta());
            u.setRestricoesReligiosas(dados.getRestricoesReligiosas());
            u.setAlergias(dados.getAlergias());
            u.setRotinaAtividade(dados.getRotinaAtividade());
            u.setStatus(dados.getStatus());
            
            Usuario salvo = usuarioRepository.save(u);
            return ResponseEntity.ok(salvo);  // ✅ RETORNA O OBJETO SALVO
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