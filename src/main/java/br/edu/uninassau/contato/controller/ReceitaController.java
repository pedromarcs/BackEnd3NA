package br.edu.uninassau.contato.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.Receita;
import br.edu.uninassau.contato.repository.ReceitaRepository;
@RestController
@RequestMapping("/receita")
public class ReceitaController {
    @Autowired
    private ReceitaRepository receitaRepository;
    @PostMapping
    public ResponseEntity<?> criarReceita(@RequestBody Receita receita) {
        Receita salva = receitaRepository.save(receita);
        return ResponseEntity.status(201).body(salva);
    }
    @GetMapping
    public ResponseEntity<List<Receita>> getReceitas() {
        return ResponseEntity.ok(receitaRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaPorId(@PathVariable Long id) {
        return receitaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nutricionista/{idNutri}")
    public ResponseEntity<List<Receita>> getReceitasPorNutricionista(@PathVariable Long idNutri) {
        return ResponseEntity.ok(receitaRepository.findByNutricionistaIdNutri(idNutri));
    }
    @GetMapping("/tags/{tag}")
    public ResponseEntity<List<Receita>> getReceitasPorTag(@PathVariable String tag) {
        return ResponseEntity.ok(receitaRepository.findByTagsContainingIgnoreCase(tag));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> putReceita(@PathVariable Long id, @RequestBody Receita dados) {
        return receitaRepository.findById(id).map(r -> {
            r.setTitulo(dados.getTitulo());
            r.setIngredientes(dados.getIngredientes());
            r.setModoPreparo(dados.getModoPreparo());
            r.setCalorias(dados.getCalorias());
            r.setProteinas(dados.getProteinas());
            r.setCarbos(dados.getCarbos());
            r.setGorduras(dados.getGorduras());
            r.setTempoPreparo(dados.getTempoPreparo());
            r.setTags(dados.getTags());
            r.setObservacoes(dados.getObservacoes());
            r.setImagemUrl(dados.getImagemUrl());
            Receita atualizada = receitaRepository.save(r);
            return ResponseEntity.ok(atualizada);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        if (receitaRepository.existsById(id)) {
            receitaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}