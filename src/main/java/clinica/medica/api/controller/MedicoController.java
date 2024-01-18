package clinica.medica.api.controller;

import clinica.medica.api.model.Medico;
import clinica.medica.api.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> cadastrarMedico(@RequestBody Medico novoMedico){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.medicoRepository.save(novoMedico));
    }

    @GetMapping("/{cpf}")
    public Medico verMedicoPeloCpf(@PathVariable String cpf){
        return this.medicoRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Medico NOT FOUND"));
    }

    @GetMapping
    public List<Medico> verTodosMedicos(){
        return this.medicoRepository.findAll();
    }

}
