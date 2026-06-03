package cl.duoc.mineria.turnos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
        } catch (Exception e) {
            System.out.println("⚠️ [Turnos] Sin conexión con Usuarios (8081). Fallback de desarrollo activo.");
            return true; 
        }
    }
}