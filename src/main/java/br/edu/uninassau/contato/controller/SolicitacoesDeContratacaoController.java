package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.SolicitacoesDeContratacao;
import br.edu.uninassau.contato.entity.Usuario;
import br.edu.uninassau.contato.dto.SolicitacoesDeContratacaoRequestDTO;
import br.edu.uninassau.contato.entity.Nutricionista;
import br.edu.uninassau.contato.repository.SolicitacoesDeContratacaoRepository;
import br.edu.uninassau.contato.repository.UsuarioRepository;
import br.edu.uninassau.contato.repository.NutricionistaRepository;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacoesDeContratacaoController {

    @Autowired
    private SolicitacoesDeContratacaoRepository solicitacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @PostMapping
    public ResponseEntity<?> criarSolicitacao(@RequestBody SolicitacoesDeContratacaoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        if (usuario == null) return ResponseEntity.status(404).body("Usuário não encontrado.");

        Nutricionista nutricionista = nutricionistaRepository.findById(dto.getNutricionistaId()).orElse(null);
        if (nutricionista == null) return ResponseEntity.status(404).body("Nutricionista não encontrado.");

        SolicitacoesDeContratacao solicitacao = new SolicitacoesDeContratacao();
        solicitacao.setUsuario(usuario);
        solicitacao.setNutricionista(nutricionista);
        solicitacao.setDataSolicitacao(dto.getDataSolicitacao());
        solicitacao.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pendente");

        SolicitacoesDeContratacao salva = solicitacaoRepository.save(solicitacao);
        return ResponseEntity.status(201).body(salva);
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