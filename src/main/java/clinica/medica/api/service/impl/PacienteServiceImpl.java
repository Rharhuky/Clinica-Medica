package clinica.medica.api.service.impl;

import clinica.medica.api.infra.repository.PacienteRepository;
import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.dto.DadosRegistroPaciente;
import clinica.medica.api.service.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;
    @Override
    public Paciente getPacienteById(Long id) {
        log.info("Recurando paciente de id {} ", id);
        log.error("Recursion ?");
        return this.pacienteRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Paciente registrarPaciente(DadosRegistroPaciente dadosRegistroPaciente) {
        log.info("Save a Paciente with that data: {}", dadosRegistroPaciente);
        return this.pacienteRepository.save(mapToPaciente(dadosRegistroPaciente));
    }

    private Paciente mapToPaciente(DadosRegistroPaciente dadosRegistroPaciente){
        return this.modelMapper.map(dadosRegistroPaciente, Paciente.class);
    }

    @Override
    public List<Paciente> getAllPacientes() {
        return this.pacienteRepository.findAll();
    }
}
