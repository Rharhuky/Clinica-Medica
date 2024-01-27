package clinica.medica.api.service;

import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.infra.repository.MedicoRepository;
import clinica.medica.api.infra.repository.PacienteRepository;
import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.Medico;
import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.Ramo;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;
import clinica.medica.api.model.dto.MotivoCancelamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class ConsultaServiceImpl implements ConsultaService{

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;


    @Override
    @Transactional
    public Consulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        Paciente paciente = pacienteRepository.findById(dadosAgendamentoConsulta.idPaciente()).orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));
        Medico medico = buscarMedicoQualquer(dadosAgendamentoConsulta);

        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setData(dadosAgendamentoConsulta.data());

        return this.consultaRepository.save(novaConsulta);
    }


    private Medico buscarMedicoQualquer(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if(Objects.nonNull(dadosAgendamentoConsulta.idMedico())){
            return this.medicoRepository.findById(dadosAgendamentoConsulta.idMedico()).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        }

        if(Objects.isNull(dadosAgendamentoConsulta.ramo()))
            throw new RuntimeException("Deve-se informar, pelo menos, o ramo de atuação do médico");


        return medicoRepository.escolherMedicoDisponivel(dadosAgendamentoConsulta.ramo(), dadosAgendamentoConsulta.data());
    }

    @Override
    @Transactional
    public void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamentoConsulta) {

        var consulta = this.consultaRepository.findById(dadosCancelamentoConsulta.idConsulta()).orElseThrow(RuntimeException::new);
        consulta.setMotivoCancelamento(dadosCancelamentoConsulta.motivoCancelamento());


    }
}
