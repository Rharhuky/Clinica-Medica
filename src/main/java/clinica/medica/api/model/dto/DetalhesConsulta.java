package clinica.medica.api.model.dto;

import java.time.LocalDateTime;

public record DetalhesConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {
}
