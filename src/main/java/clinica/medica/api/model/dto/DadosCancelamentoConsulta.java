package clinica.medica.api.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(

        @NotNull
        @JsonAlias(value = {"id_consulta"})
        Long idConsulta,

        @NotNull
        @JsonAlias(value = {"motivo"})
        MotivoCancelamento motivoCancelamento) {

}
