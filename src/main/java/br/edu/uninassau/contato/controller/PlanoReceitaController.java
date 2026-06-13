package br.edu.uninassau.contato.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import br.edu.uninassau.contato.dto.PlanoReceitaRequestDTO;
import br.edu.uninassau.contato.entity.PlanoReceita;
import br.edu.uninassau.contato.entity.PlanoAlimentar;
import br.edu.uninassau.contato.entity.Receita;
import br.edu.uninassau.contato.repository.PlanoReceitaRepository;
import br.edu.uninassau.contato.repository.PlanoAlimentarRepository;
import br.edu.uninassau.contato.repository.ReceitaRepository;

@RestController
@RequestMapping("/plano-receita")
public class PlanoReceitaController {
    @Autowired
    private PlanoReceitaRepository planoReceitaRepository;
    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;
    @Autowired
    private ReceitaRepository receitaRepository;

    @PostMapping
    public ResponseEntity<?> vincularReceita(@RequestBody PlanoReceitaRequestDTO dto) {
        PlanoAlimentar plano = planoAlimentarRepository.findById(dto.getIdPlano()).orElse(null);
        if (plano == null) {
            return ResponseEntity.status(404).body("Plano alimentar não encontrado.");
        }

        Receita receita = receitaRepository.findById(dto.getIdReceita()).orElse(null);
        if (receita == null) {
            return ResponseEntity.status(404).body("Receita não encontrada.");
        }

        try {
            PlanoReceita planoReceita = new PlanoReceita();
            planoReceita.setPlano(plano);
            planoReceita.setReceita(receita);
            planoReceita.setDataInclusao(dto.getDataInclusao() != null ? dto.getDataInclusao() : java.time.LocalDate.now());
            planoReceita.setCreatedAt(LocalDateTime.now());

            PlanoReceita salva = planoReceitaRepository.save(planoReceita);
            return ResponseEntity.status(201).body(salva);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao vincular receita: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PlanoReceita>> obterTodas() {
        return ResponseEntity.ok(planoReceitaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoReceita> obterPorId(@PathVariable Long id) {
        return planoReceitaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desvinculaReceita(@PathVariable Long id) {
        if (planoReceitaRepository.existsById(id)) {
            planoReceitaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}