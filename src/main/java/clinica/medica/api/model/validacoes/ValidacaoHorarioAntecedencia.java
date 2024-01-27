package clinica.medica.api.model.validacoes;

import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacaoHorarioAntecedencia {

    // Validacao em classes separadas ...
    public void validar(DadosAgendamentoConsulta dados){


        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaMinutos > 30){
            throw new ValidacaoException("Necessária antecedência mńimia, para agendamento de consulta, de 30 minutos");
        }



    }

}
