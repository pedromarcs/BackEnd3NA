package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.Evolucao;
import br.edu.uninassau.contato.repository.EvolucaoRepository;

@RestController
@RequestMapping("/evolucao")
public class EvolucaoController {

    @Autowired
    private EvolucaoRepository evolucaoRepository;

    @PostMapping
    public ResponseEntity<String> criarEvolucao(@RequestBody Evolucao evolucao) {
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