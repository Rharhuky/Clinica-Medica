package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ValidacaoMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){

        var medicoPossuiOutraConsultaMesmoHorario = consultaRepository.existsByMedicoMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaMesmoHorario)
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
    }

}
