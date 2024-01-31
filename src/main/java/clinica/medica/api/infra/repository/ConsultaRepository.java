package clinica.medica.api.infra.repository;

import clinica.medica.api.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


    Boolean existsByMedicoMedicoIdAndDataAndMotivoCancelamentoIsNull(Long medicoId, LocalDateTime data); // talvez nao funcione ... devido ao medico_id na coluna medico

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
