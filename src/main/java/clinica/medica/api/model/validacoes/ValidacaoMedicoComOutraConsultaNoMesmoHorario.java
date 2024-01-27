package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;

public class ValidacaoMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){

        var medicoPossuiOutraConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaMesmoHorario)
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
    }

}