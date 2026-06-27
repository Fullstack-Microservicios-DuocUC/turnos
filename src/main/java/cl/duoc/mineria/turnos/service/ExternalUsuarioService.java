package cl.duoc.mineria.turnos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import cl.duoc.mineria.turnos.exception.ServicioExternoNoDisponibleException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExternalUsuarioService {

    private final WebClient webClient;

    public boolean verificarUsuarioExiste(Long usuarioId) {
    try {
        Boolean existe = webClient.get()
                .uri("http://localhost:8081/api/v1/usuarios/existe/" + usuarioId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return existe != null && existe;
    } catch (WebClientResponseException.NotFound e) {
        return false;
    } catch (Exception e) {
        throw new ServicioExternoNoDisponibleException(
            "No se pudo validar el usuario " + usuarioId + ": " + e.getMessage());
    }
}
}