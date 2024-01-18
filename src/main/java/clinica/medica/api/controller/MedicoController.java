package clinica.medica.api.controller;

import clinica.medica.api.model.Medico;
import clinica.medica.api.model.dto.MedicoDTO;
import clinica.medica.api.repository.MedicoRepository;
import clinica.medica.api.service.MedicoService;
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

    private final MedicoService medicoService;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> cadastrarMedico(@RequestBody MedicoDTO novoMedico){
       this.medicoService.salvarMedico(novoMedico);

        return ResponseEntity.status(HttpStatus.CREATED).body("Salvo com sucesso");
    }

    @GetMapping("/{cpf}")
    public Medico verMedicoPeloCpf(@PathVariable String cpf){

        return null;

    }
    /**
    @GetMapping
    public List<Medico> verTodosMedicos(){
        return this.medicoRepository.findAll();
    }
    */
}
