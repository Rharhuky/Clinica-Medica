package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.MedicoRepository;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;

public class ValidacaoMedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() == null)
            return;

        var medicoAtivo = medicoRepository.medicoIsAtivo(dados.idMedico());
        if(!medicoAtivo)
            throw new ValidacaoException("Médico inativo!");
    }


}
