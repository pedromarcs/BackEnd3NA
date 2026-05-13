package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import br.edu.uninassau.contato.entity.Receita;
import br.edu.uninassau.contato.repository.ReceitaRepository;

@RestController
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @PostMapping("/receita")
    public ResponseEntity<String> criarReceita(@RequestBody Receita receita) {
        receitaRepository.save(receita);
        return ResponseEntity.status(201).body("Receita criada com sucesso!");
    }

    @GetMapping("/receita")
    public ResponseEntity<List<Receita>> getReceitas() {
        return ResponseEntity.ok(receitaRepository.findAll());
    }

    @PutMapping("/receita/{id}")
    public ResponseEntity<String> putReceita(@PathVariable Long id, @RequestBody Receita dados) {
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
            receitaRepository.save(r);
            return ResponseEntity.ok("Receita atualizada com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/receita/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        if (receitaRepository.existsById(id)) {
            receitaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}