package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import br.edu.uninassau.contato.entity.Nutricionista;
import br.edu.uninassau.contato.repository.NutricionistaRepository;

@RestController
public class NutricionistaController {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @PostMapping("/nutricionista")
    public ResponseEntity<String> criarNutricionista(@RequestBody Nutricionista nutricionista) {
        nutricionistaRepository.save(nutricionista);
        return ResponseEntity.status(201).body("Nutricionista criado com sucesso!");
    }

    @GetMapping("/nutricionista")
    public ResponseEntity<List<Nutricionista>> getNutricionistas() {
        return ResponseEntity.ok(nutricionistaRepository.findAll());
    }

    @PutMapping("/nutricionista/{id}")
    public ResponseEntity<String> putNutricionista(@PathVariable Long id, @RequestBody Nutricionista dados) {
        return nutricionistaRepository.findById(id).map(n -> {
            n.setNome(dados.getNome());
            n.setEmail(dados.getEmail());
            n.setEspecialidade(dados.getEspecialidade());
            n.setUf(dados.getUf());
            n.setBiografia(dados.getBiografia());
            nutricionistaRepository.save(n);
            return ResponseEntity.ok("Nutricionista atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/nutricionista/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        if (nutricionistaRepository.existsById(id)) {
            nutricionistaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}