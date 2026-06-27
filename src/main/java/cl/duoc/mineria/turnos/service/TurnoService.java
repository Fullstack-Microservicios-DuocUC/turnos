package cl.duoc.mineria.turnos.service;

import cl.duoc.mineria.turnos.dto.AbrirTurnoDTO;
import cl.duoc.mineria.turnos.exception.TurnoNotFoundException;
import cl.duoc.mineria.turnos.exception.UsuarioNotFoundException;
import cl.duoc.mineria.turnos.mapper.TurnoMapper;
import cl.duoc.mineria.turnos.model.EstadoTurno;
import cl.duoc.mineria.turnos.model.Turno;
import cl.duoc.mineria.turnos.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final TurnoMapper turnoMapper;
    private final ExternalUsuarioService externalUsuarioService;

    @Transactional
    public Turno abrirTurno(AbrirTurnoDTO dto) {
        if (!externalUsuarioService.verificarUsuarioExiste(dto.getUsuarioId())) {
            throw new UsuarioNotFoundException("No se puede iniciar turno: El ID de usuario " + dto.getUsuarioId() + " no existe.");
        }

        Turno nuevoTurno = turnoMapper.toEntity(dto);
        return turnoRepository.save(nuevoTurno);
    }

    @Transactional
    public Turno finalizarTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
            .orElseThrow(() -> new TurnoNotFoundException("El turno con ID " + id + " no existe en los registros."));

        turno.setFechaHoraFin(LocalDateTime.now());
        turno.setEstado(EstadoTurno.FINALIZADO);
        return turnoRepository.save(turno);
    }

    @Transactional(readOnly = true)
    public List<Turno> listarPorUsuario(Long usuarioId) {
        return turnoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public boolean existeTurno(Long id) {
        return turnoRepository.existsById(id);
    }
}