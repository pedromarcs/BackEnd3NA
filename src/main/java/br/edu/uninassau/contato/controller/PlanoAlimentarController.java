package br.edu.uninassau.contato.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.PlanoAlimentar;
import br.edu.uninassau.contato.repository.PlanoAlimentarRepository;
@RestController
@RequestMapping("/plano")
public class PlanoAlimentarController {
    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;
    @PostMapping
    public ResponseEntity<?> criarPlano(@RequestBody PlanoAlimentar plano) {
        PlanoAlimentar salvo = planoAlimentarRepository.save(plano);
        return ResponseEntity.status(201).body(salvo);
    }
    @GetMapping
    public ResponseEntity<List<PlanoAlimentar>> getPlanos() {
        return ResponseEntity.ok(planoAlimentarRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> getPlanoPorId(@PathVariable Long id) {
        return planoAlimentarRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<PlanoAlimentar>> getPlanosPorUsuario(@PathVariable Long idUser) {
        return ResponseEntity.ok(planoAlimentarRepository.findByUsuarioIdUser(idUser));
    }
    @GetMapping("/nutricionista/{idNutri}")
    public ResponseEntity<List<PlanoAlimentar>> getPlanosPorNutricionista(@PathVariable Long idNutri) {
        return ResponseEntity.ok(planoAlimentarRepository.findByNutricionistaIdNutri(idNutri));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> putPlano(@PathVariable Long id, @RequestBody PlanoAlimentar dados) {
        return planoAlimentarRepository.findById(id).map(p -> {
            p.setCaloriasAlvo(dados.getCaloriasAlvo());
            p.setProteinaAlvo(dados.getProteinaAlvo());
            p.setGorduraAlvo(dados.getGorduraAlvo());
            p.setCarboAlvo(dados.getCarboAlvo());
            p.setStatus(dados.getStatus());
            PlanoAlimentar atualizado = planoAlimentarRepository.save(p);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Long id) {
        if (planoAlimentarRepository.existsById(id)) {
            planoAlimentarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}