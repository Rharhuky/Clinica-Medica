package clinica.medica.api.service;

import clinica.medica.api.infra.repository.ConsultaRepository;
import clinica.medica.api.infra.repository.MedicoRepository;
import clinica.medica.api.infra.repository.PacienteRepository;
import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.Medico;
import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.Ramo;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor

@Service
public class ConsultaServiceImpl implements ConsultaService{

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;


    @Override
    @Transactional
    public DetalhesConsulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        Paciente paciente = pacienteRepository.findById(dadosAgendamentoConsulta.idPaciente()).orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));
        Medico medico = buscarMedicoQualquer(dadosAgendamentoConsulta);

        Consulta novaConsulta = new Consulta();
        novaConsulta.setMedico(medico);
        novaConsulta.setPaciente(paciente);

//        return new DetalhesConsulta(this.consultaRepository.save(novaConsulta));
        return null;
    }


    private Medico buscarMedicoQualquer(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if(Objects.nonNull(dadosAgendamentoConsulta.idMedico())){
            return this.medicoRepository.findById(dadosAgendamentoConsulta.idMedico()).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        }

        if(Objects.isNull(dadosAgendamentoConsulta.ramo()))
            throw new RuntimeException("Deve-se informar, pelo menos, o ramo de atuação do médico");


        return medicoRepository.escolherMedicoDisponivel(dadosAgendamentoConsulta.ramo(), dadosAgendamentoConsulta.data());
    }


}
