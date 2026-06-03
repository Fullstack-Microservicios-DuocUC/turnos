package cl.duoc.mineria.turnos.controller;

import cl.duoc.mineria.turnos.dto.AbrirTurnoDTO;
import cl.duoc.mineria.turnos.model.Turno;
import cl.duoc.mineria.turnos.service.TurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping("/abrir")
    public ResponseEntity<Turno> abrir(@Valid @RequestBody AbrirTurnoDTO dto) {
        return new ResponseEntity<>(turnoService.abrirTurno(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Turno> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.finalizarTurno(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Turno>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(turnoService.listarPorUsuario(usuarioId));
    }

    // Consumido vía WebClient por el servicio de Reportes
    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> verificarExiste(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.existeTurno(id));
    }
}