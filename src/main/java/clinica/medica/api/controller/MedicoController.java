package clinica.medica.api.controller;

import clinica.medica.api.model.Medico;
import clinica.medica.api.model.dto.DadosListagemMedicos;
import clinica.medica.api.model.dto.MedicoDTO;
import clinica.medica.api.repository.MedicoRepository;
import clinica.medica.api.service.MedicoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<?> cadastrarMedico(@RequestBody @Valid MedicoDTO novoMedico){
       this.medicoService.salvarMedico(novoMedico);

        return ResponseEntity.status(HttpStatus.CREATED).body("Salvo com sucesso");
    }
    @GetMapping
    public ResponseEntity<List<DadosListagemMedicos>> listarMedicos(
            @RequestParam(value = "ordenarPelo"    ,        defaultValue = "nome",     required = false) String ordenarPelo,
            @RequestParam(value = "forma",        defaultValue = "ASC",      required = false) String ordenarDeForma,
            @RequestParam(value = "numeroDaPagina" ,        defaultValue = "0",        required = false)   int numeroPagina,
            @RequestParam(value = "tamanhoDaPagina",        defaultValue = "10",       required = false)   int tamanhoPagina
            ){

        System.out.println(ordenarDeForma + " " + numeroPagina + " " + tamanhoPagina + " " + ordenarPelo);
        return  ResponseEntity.ok( this.medicoService.verTodosMedicos(ordenarPelo, ordenarDeForma, numeroPagina, tamanhoPagina) );
    }

}
