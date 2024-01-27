package clinica.medica.api.service;

import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.dto.DadosRegistroPaciente;

import java.util.List;

public interface PacienteService {

    Paciente getPacienteById(Long id);

    Paciente registrarPaciente(DadosRegistroPaciente dadosRegistroPaciente);

    List<Paciente> getAllPacientes();

}
