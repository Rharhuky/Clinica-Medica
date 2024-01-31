package clinica.medica.api.model.dto;

import clinica.medica.api.model.Ramo;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        @JsonAlias(value = {"medico_id", "id_medico"})
        Long idMedico,
        @NotNull
        @JsonAlias(value = {"paciente_id"})
        Long idPaciente,
        @Future
        @JsonAlias(value = "data")
        LocalDateTime data,

        @JsonAlias(value = {"especialidade", "atuacao"})
        Ramo ramo
) {

}
