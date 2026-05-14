package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.VinculoNutriPaciente;
import br.edu.uninassau.contato.repository.VinculoNutriPacienteRepository;

@RestController
@RequestMapping("/vinculo")
public class VinculoNutriPacienteController {

    @Autowired
    private VinculoNutriPacienteRepository vinculoRepository;

    @PostMapping
    public ResponseEntity<String> criarVinculo(@RequestBody VinculoNutriPaciente vinculo) {
        vinculoRepository.save(vinculo);
        return ResponseEntity.status(201).body("Vínculo criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<VinculoNutriPaciente>> getVinculos() {
        return ResponseEntity.ok(vinculoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VinculoNutriPaciente> getVinculoPorId(@PathVariable Long id) {
        return vinculoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<VinculoNutriPaciente>> getVinculosPorUsuario(@PathVariable Long idUser) {
        return ResponseEntity.ok(vinculoRepository.findByUsuarioIdUser(idUser));
    }

    @GetMapping("/nutricionista/{idNutri}")
    public ResponseEntity<List<VinculoNutriPaciente>> getVinculosPorNutricionista(@PathVariable Long idNutri) {
        return ResponseEntity.ok(vinculoRepository.findByNutricionistaIdNutri(idNutri));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putVinculo(@PathVariable Long id, @RequestBody VinculoNutriPaciente dados) {
        return vinculoRepository.findById(id).map(v -> {
            v.setStatus(dados.getStatus());
            v.setDataAprovacao(dados.getDataAprovacao());
            vinculoRepository.save(v);
            return ResponseEntity.ok("Vínculo atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVinculo(@PathVariable Long id) {
        if (vinculoRepository.existsById(id)) {
            vinculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}