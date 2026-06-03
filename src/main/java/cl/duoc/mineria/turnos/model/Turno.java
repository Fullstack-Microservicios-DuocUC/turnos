package cl.duoc.mineria.turnos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTurno estado;
}