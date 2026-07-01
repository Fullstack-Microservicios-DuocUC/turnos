package cl.duoc.mineria.turnos.controller;

import cl.duoc.mineria.turnos.dto.AbrirTurnoDTO;
import cl.duoc.mineria.turnos.model.Turno;
import cl.duoc.mineria.turnos.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
@Tag(name = "Gestión de Turnos", description = "Operaciones para administrar el ciclo de vida de los turnos de trabajo.")
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping("/abrir")
    @Operation(summary = "Abrir un nuevo turno", description = "Registra el inicio de un nuevo turno para un usuario en una fecha y hora específicas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno abierto exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o el usuario ya tiene un turno abierto")
    })
    public ResponseEntity<Turno> abrir(@Valid @RequestBody AbrirTurnoDTO dto) {
        return new ResponseEntity<>(turnoService.abrirTurno(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar un turno existente", description = "Marca un turno como finalizado, registrando la fecha y hora de término.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno finalizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado con el ID proporcionado")
    })
    public ResponseEntity<Turno> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.finalizarTurno(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar turnos por usuario", description = "Obtiene un historial de todos los turnos asociados a un ID de usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de turnos obtenida con éxito")
    })
    public ResponseEntity<List<Turno>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(turnoService.listarPorUsuario(usuarioId));
    }

    // Consumido vía WebClient por el servicio de Reportes
    @GetMapping("/existe/{id}")
    @Operation(summary = "Verificar si un turno existe", description = "Endpoint de utilidad para otros microservicios. Devuelve 'true' si el turno con el ID existe, 'false' en caso contrario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verificación realizada, el cuerpo de la respuesta contiene el booleano")
    })
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.existeTurno(id));
    }
}