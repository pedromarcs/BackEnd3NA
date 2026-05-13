package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import br.edu.uninassau.contato.entity.PlanoAlimentar;
import br.edu.uninassau.contato.repository.PlanoAlimentarRepository;

@RestController
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;

    @PostMapping("/plano")
    public ResponseEntity<String> criarPlano(@RequestBody PlanoAlimentar plano) {
        planoAlimentarRepository.save(plano);
        return ResponseEntity.status(201).body("Plano alimentar criado com sucesso!");
    }

    @GetMapping("/plano")
    public ResponseEntity<List<PlanoAlimentar>> getPlanos() {
        return ResponseEntity.ok(planoAlimentarRepository.findAll());
    }

    @PutMapping("/plano/{id}")
    public ResponseEntity<String> putPlano(@PathVariable Long id, @RequestBody PlanoAlimentar dados) {
        return planoAlimentarRepository.findById(id).map(p -> {
            p.setCaloriasAlvo(dados.getCaloriasAlvo());
            p.setProteinaAlvo(dados.getProteinaAlvo());
            p.setGorduraAlvo(dados.getGorduraAlvo());
            p.setCarboAlvo(dados.getCarboAlvo());
            p.setStatus(dados.getStatus());
            planoAlimentarRepository.save(p);
            return ResponseEntity.ok("Plano alimentar atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/plano/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Long id) {
        if (planoAlimentarRepository.existsById(id)) {
            planoAlimentarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}