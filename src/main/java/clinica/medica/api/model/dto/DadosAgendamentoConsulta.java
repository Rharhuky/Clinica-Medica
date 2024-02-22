package clinica.medica.api.model.dto;

import clinica.medica.api.model.Ramo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"data","idPaciente", "idMedico", "ramo"})
public record DadosAgendamentoConsulta(

        @JsonAlias(value = {"medico_id", "id_medico"})
        @JsonProperty(value = "id_medico")
        Long idMedico,

        @NotNull
        @JsonAlias(value = {"paciente_id"})
        @JsonProperty(value = "id_paciente")
        Long idPaciente,

        @Future
        @JsonAlias(value = "data")
        LocalDateTime data,

        @JsonProperty(value = "especialidade")
        @JsonAlias(value = {"especialidade", "atuacao"})
        Ramo ramo
) {

}
