package clinica.medica.api.service.interfaces;

import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;

import java.util.List;

public interface ConsultaService {

   DetalhesConsulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta);

    void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamentoConsulta);

    List<DetalhesConsulta> getAll();

    void cancelar(DadosCancelamentoConsulta dadosCancelamentoConsulta);
}
