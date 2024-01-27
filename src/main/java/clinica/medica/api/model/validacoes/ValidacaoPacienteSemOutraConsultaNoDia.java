package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;

public class ValidacaoPacienteSemOutraConsultaNoDia {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){

        var primerioHorario = dadosAgendamentoConsulta.data().withHour(8);
        var ultimoHorario = dadosAgendamentoConsulta.data().withHour(18);
        var pacienteTemOutraConsultaNoDia =
                consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(), primerioHorario, ultimoHorario);

        if(pacienteTemOutraConsultaNoDia)
            throw new ValidacaoException("Paciente já possui consulta marcada nessa data");

    }

}
