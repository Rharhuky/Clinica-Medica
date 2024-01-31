package clinica.medica.api.model.validacoes.agendamento;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ValidacaoPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta{

    private final ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){

        var primerioHorario = dados.data().withHour(8);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteTemOutraConsultaNoDia =
                consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primerioHorario, ultimoHorario);

        if(pacienteTemOutraConsultaNoDia)
            throw new ValidacaoException("Paciente já possui consulta marcada nessa data");

    }

}
