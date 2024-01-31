package clinica.medica.api.service.impl;

import clinica.medica.api.infra.handler.MedicoNaoEncontradoException;
import clinica.medica.api.infra.handler.PacienteNaoEncontradoException;
import clinica.medica.api.infra.handler.ValidacaoException;
import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.infra.repository.MedicoRepository;
import clinica.medica.api.infra.repository.PacienteRepository;
import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.Medico;
import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;
import clinica.medica.api.model.validacoes.agendamento.ValidadorAgendamentoConsulta;
import clinica.medica.api.model.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import clinica.medica.api.service.interfaces.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoConsulta> validadores;
    private final List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    @Override
    @Transactional
    public DetalhesConsulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        Paciente paciente = pacienteRepository.findById(dadosAgendamentoConsulta.idPaciente()).orElseThrow(() -> new PacienteNaoEncontradoException());
        Medico medico = buscarMedicoQualquer(dadosAgendamentoConsulta);
        validadores.forEach((validador) -> validador.validar(dadosAgendamentoConsulta));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setData(dadosAgendamentoConsulta.data());
        return new DetalhesConsulta( this.consultaRepository.save(novaConsulta) );
    }


    private Medico buscarMedicoQualquer(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if(Objects.nonNull(dadosAgendamentoConsulta.idMedico())){
            return this.medicoRepository.findById(dadosAgendamentoConsulta.idMedico()).orElseThrow(MedicoNaoEncontradoException::new);
        }
        if(Objects.isNull(dadosAgendamentoConsulta.ramo()))
            throw new ValidacaoException("Deve-se informar, pelo menos, o ramo de atuação do médico");

        return medicoRepository.escolherMedicoDisponivel(String.valueOf(dadosAgendamentoConsulta.ramo()), dadosAgendamentoConsulta.data()).orElseThrow(
                () -> new ValidacaoException("Não há médico com essa especialidade disponível")

        );
    }

    @Override
    @Transactional
    public void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamentoConsulta) {

        var consulta = this.consultaRepository.findById(dadosCancelamentoConsulta.idConsulta()).orElseThrow(RuntimeException::new);
        consulta.setMotivoCancelamento(dadosCancelamentoConsulta.motivoCancelamento());
        this.consultaRepository.save(consulta);
    }

    @Override
    public List<DetalhesConsulta> getAll() {

        var consultas = this.consultaRepository.findAll();
        List<DetalhesConsulta> detalhesConsultas = consultas.stream().map(DetalhesConsulta::new).toList();
        return detalhesConsultas;
    }

    @Override
    public void cancelar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {

        if(!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta()))
            throw new ValidacaoException("Consulta não encontrada!");

        validadoresCancelamento.forEach((v) -> v.validar(dadosCancelamentoConsulta));


    }
}
