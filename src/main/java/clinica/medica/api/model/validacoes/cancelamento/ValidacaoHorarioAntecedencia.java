package clinica.medica.api.model.validacoes.cancelamento;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service("ValidacaoHorarioAntecedenciaCancelamento")
public class ValidacaoHorarioAntecedencia implements ValidadorCancelamentoConsulta{

    private final ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        var consulta = consultaRepository.findById(dadosCancelamentoConsulta.idConsulta()).orElseThrow(() -> new ValidacaoException("Consulta nao encontrada"));
        var agora = LocalDate.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
        if(diferencaEmHoras < 24)
            throw new ValidacaoException("Consulta não pode ser cancelada em menos de 24 horas");
    }
}
