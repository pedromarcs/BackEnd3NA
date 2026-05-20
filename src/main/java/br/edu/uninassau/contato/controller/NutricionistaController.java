package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.Nutricionista;
import br.edu.uninassau.contato.repository.NutricionistaRepository;

@RestController
@RequestMapping("/nutricionista")
public class NutricionistaController {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @PostMapping
    public ResponseEntity<String> criarNutricionista(@RequestBody Nutricionista nutricionista) {
        nutricionistaRepository.save(nutricionista);
        return ResponseEntity.status(201).body("Nutricionista criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Nutricionista dados) {
        return nutricionistaRepository.findByEmailProfissional(dados.getEmailProfissional())
            .map(n -> {
                if (n.getSenhaHash().equals(dados.getSenhaHash())) {
                    return ResponseEntity.ok(n);
                }
                return ResponseEntity.status(401).body("Senha incorreta!");
            })
            .orElse(ResponseEntity.status(404).body("Nutricionista não encontrado!"));
    }

    @GetMapping
    public ResponseEntity<List<Nutricionista>> getNutricionistas() {
        return ResponseEntity.ok(nutricionistaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> getNutricionistaPorId(@PathVariable Long id) {
        return nutricionistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putNutricionista(@PathVariable Long id, @RequestBody Nutricionista dados) {
        return nutricionistaRepository.findById(id).map(n -> {
            n.setNomeCompleto(dados.getNomeCompleto());
            n.setEmailProfissional(dados.getEmailProfissional());
            n.setEspecialidadePrincipal(dados.getEspecialidadePrincipal());
            n.setUf(dados.getUf());
            n.setBiografia(dados.getBiografia());
            n.setCrn(dados.getCrn());
            n.setAvaliacaoMedia(dados.getAvaliacaoMedia());
            n.setTotalPacientes(dados.getTotalPacientes());
            nutricionistaRepository.save(n);
            return ResponseEntity.ok("Nutricionista atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        if (nutricionistaRepository.existsById(id)) {
            nutricionistaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}