package com.example.backend3nareplica.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import com.example.backend3nareplica.dto.ConsumoRequest;
import com.example.backend3nareplica.dto.ConsumoResponse;
import com.example.backend3nareplica.entity.Receita;
import com.example.backend3nareplica.entity.Usuario;
import com.example.backend3nareplica.repository.ReceitaRepository;
import com.example.backend3nareplica.repository.UsuarioRepository;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReceitaRepository receitaRepository;
    @PostMapping
    public ResponseEntity<String> criarUsuario(@Valid @RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body("Usuário criado com sucesso!");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario dados) {
        return usuarioRepository.findByEmail(dados.getEmail())
            .map(u -> {
                if (u.getSenhaHash().equals(dados.getSenhaHash())) {
                    return ResponseEntity.ok(u);
                }
                return ResponseEntity.status(401).body("Senha incorreta!");
            })
            .orElse(ResponseEntity.status(404).body("Usuário não encontrado!"));
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
  @PutMapping("/{id}")
public ResponseEntity<String> putUsuario(@PathVariable Long id, @RequestBody Usuario dados) {
    return usuarioRepository.findById(id).map(u -> {
        u.setNomeCompleto(dados.getNomeCompleto());
        u.setEmail(dados.getEmail());
        u.setDataNascimento(dados.getDataNascimento());
        u.setObjetivoSaude(dados.getObjetivoSaude());
        u.setTipoDieta(dados.getTipoDieta());
        u.setRestricoesReligiosas(dados.getRestricoesReligiosas());
        u.setAlergias(dados.getAlergias());
        u.setRotinaAtividade(dados.getRotinaAtividade());
        u.setPesoAtual(dados.getPesoAtual());
        u.setPesoMeta(dados.getPesoMeta());
        u.setAltura(dados.getAltura());
        u.setStatus(dados.getStatus());
        u.setCaloriasDiarias(dados.getCaloriasDiarias());
        if (dados.getGenero() != null) u.setGenero(dados.getGenero()); // <-- ADICIONA
        if (dados.getFotoUrl() != null) u.setFotoUrl(dados.getFotoUrl());
        usuarioRepository.save(u);
        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }).orElse(ResponseEntity.notFound().build());
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/consumo")
    @Transactional
    public ResponseEntity<ConsumoResponse> registrarConsumo(@PathVariable Long id, @RequestBody ConsumoRequest req) {
        if (req == null || req.idReceita() == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        Receita receita = receitaRepository.findById(req.idReceita()).orElse(null);
        if (usuario == null || receita == null) {
            return ResponseEntity.notFound().build();
        }

        LocalDate hoje = LocalDate.now(FUSO);
        if (!hoje.equals(usuario.getDataConsumo())) {
            usuario.setCaloriasConsumidas(BigDecimal.ZERO);
            usuario.setProteinasConsumidas(BigDecimal.ZERO);
            usuario.setCarbosConsumidos(BigDecimal.ZERO);
            usuario.setGordurasConsumidas(BigDecimal.ZERO);
            usuario.setDataConsumo(hoje);
        }

        usuario.setCaloriasConsumidas(usuario.getCaloriasConsumidas().add(valor(receita.getCalorias())));
        usuario.setProteinasConsumidas(usuario.getProteinasConsumidas().add(valor(receita.getProteinas())));
        usuario.setCarbosConsumidos(usuario.getCarbosConsumidos().add(valor(receita.getCarbos())));
        usuario.setGordurasConsumidas(usuario.getGordurasConsumidas().add(valor(receita.getGorduras())));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(new ConsumoResponse(
                hoje,
                usuario.getCaloriasConsumidas(),
                usuario.getProteinasConsumidas(),
                usuario.getCarbosConsumidos(),
                usuario.getGordurasConsumidas()
        ));
    }

    @GetMapping("/{id}/consumo")
    public ResponseEntity<ConsumoResponse> getConsumoDeHoje(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            LocalDate hoje = LocalDate.now(FUSO);
            if (!hoje.equals(usuario.getDataConsumo())) {
                return ResponseEntity.ok(new ConsumoResponse(
                        hoje, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
                ));
            }
            return ResponseEntity.ok(new ConsumoResponse(
                    hoje,
                    usuario.getCaloriasConsumidas(),
                    usuario.getProteinasConsumidas(),
                    usuario.getCarbosConsumidos(),
                    usuario.getGordurasConsumidas()
            ));
        }).orElse(ResponseEntity.notFound().build());
    }

    private BigDecimal valor(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
