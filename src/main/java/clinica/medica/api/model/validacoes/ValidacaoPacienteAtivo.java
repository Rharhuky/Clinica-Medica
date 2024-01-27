package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.PacienteRepository;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;

public class ValidacaoPacienteAtivo {

    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){

        var pacienteIsAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(! pacienteIsAtivo)
            throw new ValidacaoException("Paciente não ativo");

    }

}
