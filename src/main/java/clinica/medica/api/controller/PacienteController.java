package clinica.medica.api.controller;

import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.dto.DadosRegistroPaciente;
import clinica.medica.api.service.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> verPaciente(@PathVariable Long id){
        var paciente = this.pacienteService.getPacienteById(id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<?> criarPaciente(@RequestBody DadosRegistroPaciente dadosRegistroPaciente, UriComponentsBuilder uriComponentsBuilder){

        var novoPaciente = this.pacienteService.registrarPaciente(dadosRegistroPaciente);
        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(uri).body(novoPaciente);

    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getPacientes(){
        var pacientes =this.pacienteService.getAllPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);

    }



}
