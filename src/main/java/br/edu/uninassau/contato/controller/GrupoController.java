import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grupo")
@Tag(name = "Grupo", description = "Operações relacionadas a grupos")
public class GrupoController {

    @PostMapping
    @Operation(summary = "Criar um novo Grupo", description = "Cria um novo Grupo com os detalhes fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public String createGrupo() {
        return "Grupo criado";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Grupo por ID", description = "Obtém um Grupo pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Grupo encontrado"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    public String getGrupo(
        @Parameter(description = "ID do grupo a ser buscado", example = "1")
        @PathVariable Long id
    ) {
        return "Detalhes do Grupo";
    }
}
