package cl.duoc.mineria.turnos.mapper;

import cl.duoc.mineria.turnos.dto.AbrirTurnoDTO;
import cl.duoc.mineria.turnos.model.EstadoTurno;
import cl.duoc.mineria.turnos.model.Turno;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TurnoMapper {

    public Turno toEntity(AbrirTurnoDTO dto) {
        if (dto == null) return null;

        return Turno.builder()
                .usuarioId(dto.getUsuarioId())
                .fechaHoraInicio(LocalDateTime.now()) // Inicia en este instante
                .estado(EstadoTurno.ACTIVO) // Todo turno nuevo nace activo
                .build();
    }
}