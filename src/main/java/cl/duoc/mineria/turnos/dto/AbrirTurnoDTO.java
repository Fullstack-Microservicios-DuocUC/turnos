package cl.duoc.mineria.turnos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AbrirTurnoDTO {

    @NotNull(message = "El ID del usuario/trabajador es obligatorio")
    private Long usuarioId;
}