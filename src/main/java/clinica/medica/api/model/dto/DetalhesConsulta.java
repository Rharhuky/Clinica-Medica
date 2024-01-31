package clinica.medica.api.model.dto;

import clinica.medica.api.model.Consulta;
import java.time.LocalDateTime;

public record DetalhesConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {

    public DetalhesConsulta(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getMedicoId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
