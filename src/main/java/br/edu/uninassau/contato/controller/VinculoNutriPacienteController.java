package br.edu.uninassau.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.uninassau.contato.entity.VinculoNutriPaciente;
import br.edu.uninassau.contato.entity.Usuario;
import br.edu.uninassau.contato.dto.VinculoNutriPacienteRequestDTO;
import br.edu.uninassau.contato.entity.Nutricionista;
import br.edu.uninassau.contato.repository.VinculoNutriPacienteRepository;
import br.edu.uninassau.contato.repository.UsuarioRepository;
import br.edu.uninassau.contato.repository.NutricionistaRepository;

@RestController
@RequestMapping("/vinculo")
public class VinculoNutriPacienteController {

    @Autowired
    private VinculoNutriPacienteRepository vinculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @PostMapping
    public ResponseEntity<?> criarVinculo(@RequestBody VinculoNutriPacienteRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        if (usuario == null) return ResponseEntity.status(404).body("Usuário não encontrado.");

        Nutricionista nutricionista = nutricionistaRepository.findById(dto.getNutricionistaId()).orElse(null);
        if (nutricionista == null) return ResponseEntity.status(404).body("Nutricionista não encontrado.");

        VinculoNutriPaciente vinculo = new VinculoNutriPaciente();
        vinculo.setUsuario(usuario);
        vinculo.setNutricionista(nutricionista);
        vinculo.setDataSolicitacao(dto.getDataSolicitacao());
        vinculo.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pendente");

        VinculoNutriPaciente salvo = vinculoRepository.save(vinculo);
        return ResponseEntity.status(201).body(salvo);
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