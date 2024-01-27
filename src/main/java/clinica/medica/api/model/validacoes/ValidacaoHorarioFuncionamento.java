package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidacaoHorarioFuncionamento {

 private static final Float horarioAberturaClinica = 8f;
 private static final Float horarioFechamentoClinica = 18f;
    /**
     * Validar o horário de funcionamento da clínica
     * seg - sexta ( 8 AM - 7 PM) ou (08:00 - 19:00)
     * @param dados
     */
    public void validar(DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();
        var sabadoOuDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY) || dataConsulta.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var antesHorarioAberturaClinica = dados.data().getHour() < horarioAberturaClinica;
        var depoisHorarioFechamentoClinica = dados.data().getHour() > horarioFechamentoClinica;

        if(sabadoOuDomingo || antesHorarioAberturaClinica || depoisHorarioFechamentoClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }


    }

}
