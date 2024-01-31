package clinica.medica.api.model.validacoes.cancelamento;

import clinica.medica.api.model.dto.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {

    void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta);
}
