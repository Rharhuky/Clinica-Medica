package clinica.medica.api.service;

import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;

public interface ConsultaService {

    DetalhesConsulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta);

}
