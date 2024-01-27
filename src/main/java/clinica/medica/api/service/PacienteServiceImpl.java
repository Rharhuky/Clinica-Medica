package clinica.medica.api.service;

import clinica.medica.api.infra.repository.PacienteRepository;
import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.dto.DadosRegistroPaciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PacienteServiceImpl implements PacienteService{

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;
    @Override
    public Paciente getPacienteById(Long id) {
        return this.pacienteRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Paciente registrarPaciente(DadosRegistroPaciente dadosRegistroPaciente) {
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
