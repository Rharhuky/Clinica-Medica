package clinica.medica.api.service;

import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;

public interface ConsultaService {

    Consulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta);

    void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamentoConsulta);
}
