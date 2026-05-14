package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.SolicitacoesDeContratacao;
import br.edu.uninassau.contato.repository.SolicitacoesDeContratacaoRepository;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacoesDeContratacaoController {

    @Autowired
    private SolicitacoesDeContratacaoRepository solicitacaoRepository;

    @PostMapping
    public ResponseEntity<String> criarSolicitacao(@RequestBody SolicitacoesDeContratacao solicitacao) {
        solicitacaoRepository.save(solicitacao);
        return ResponseEntity.status(201).body("Solicitação criada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<SolicitacoesDeContratacao>> getSolicitacoes() {
        return ResponseEntity.ok(solicitacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacoesDeContratacao> getSolicitacaoPorId(@PathVariable Long id) {
        return solicitacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<SolicitacoesDeContratacao>> getSolicitacoesPorUsuario(@PathVariable Long idUser) {
        return ResponseEntity.ok(solicitacaoRepository.findByUsuarioIdUser(idUser));
    }

    @GetMapping("/nutricionista/{idNutri}")
    public ResponseEntity<List<SolicitacoesDeContratacao>> getSolicitacoesPorNutricionista(@PathVariable Long idNutri) {
        return ResponseEntity.ok(solicitacaoRepository.findByNutricionistaIdNutri(idNutri));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putSolicitacao(@PathVariable Long id, @RequestBody SolicitacoesDeContratacao dados) {
        return solicitacaoRepository.findById(id).map(s -> {
            s.setStatus(dados.getStatus());
            s.setDataSolicitacao(dados.getDataSolicitacao());
            solicitacaoRepository.save(s);
            return ResponseEntity.ok("Solicitação atualizada com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable Long id) {
        if (solicitacaoRepository.existsById(id)) {
            solicitacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}