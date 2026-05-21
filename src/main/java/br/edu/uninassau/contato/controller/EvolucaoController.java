package br.edu.uninassau.contato.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.uninassau.contato.dto.EvolucaoRequestDTO;
import br.edu.uninassau.contato.entity.Evolucao;
import br.edu.uninassau.contato.entity.Usuario;
import br.edu.uninassau.contato.repository.EvolucaoRepository;
import br.edu.uninassau.contato.repository.UsuarioRepository;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/evolucao")
public class EvolucaoController {

    @Autowired
    private EvolucaoRepository evolucaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> criarEvolucao(@RequestBody EvolucaoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getFkIdUser()).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }

        Evolucao evolucao = new Evolucao();
        evolucao.setUsuario(usuario);
        evolucao.setDataRegistro(LocalDate.parse(dto.getDataRegistro()));
        evolucao.setPesoRegistrado(dto.getPesoRegistrado());
        evolucao.setMetaProgresso(dto.getMetaProgresso());
        evolucao.setCreatedAt(LocalDateTime.now());

        evolucaoRepository.save(evolucao);
        return ResponseEntity.status(201).body("Evolução registrada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Evolucao>> getEvolucoes() {
        return ResponseEntity.ok(evolucaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evolucao> getEvolucaoPorId(@PathVariable Long id) {
        return evolucaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<Evolucao>> getEvolucoesPorUsuario(@PathVariable Long idUser) {
        return ResponseEntity.ok(evolucaoRepository.findByUsuarioIdUser(idUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putEvolucao(@PathVariable Long id, @RequestBody Evolucao dados) {
        return evolucaoRepository.findById(id).map(e -> {
            e.setDataRegistro(dados.getDataRegistro());
            e.setPesoRegistrado(dados.getPesoRegistrado());
            e.setMetaProgresso(dados.getMetaProgresso());
            e.setTotalCaloriasConsumidas(dados.getTotalCaloriasConsumidas());
            e.setRefeicoesConcluidasI(dados.getRefeicoesConcluidasI());
            evolucaoRepository.save(e);
            return ResponseEntity.ok("Evolução atualizada com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvolucao(@PathVariable Long id) {
        if (evolucaoRepository.existsById(id)) {
            evolucaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}