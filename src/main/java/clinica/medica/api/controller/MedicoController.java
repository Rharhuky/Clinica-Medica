package clinica.medica.api.controller;

import clinica.medica.api.model.dto.DadosAtualizarMedico;
import clinica.medica.api.model.dto.DadosListagemMedicos;
import clinica.medica.api.model.dto.MedicoDTO;
import clinica.medica.api.service.interfaces.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(path = "medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> cadastrarMedico(@RequestBody @Valid MedicoDTO novoMedico, UriComponentsBuilder uriComponentsBuilder){

        var medico = this.medicoService.salvarMedico(novoMedico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getMedicoId()).toUri();

        return ResponseEntity.created(uri).body(medico);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> listarMedico(@PathVariable Long id){
        return ResponseEntity.ok(this.medicoService.verMedico(id));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemMedicos>> listarMedicos(
            //@PageableDefault()
            @RequestParam(value = "ordenarPelo"    ,        defaultValue = "nome",     required = false) String ordenarPelo,
            @RequestParam(value = "forma"          ,        defaultValue = "ASC",      required = false) String ordenarDeForma,
            @RequestParam(value = "numeroDaPagina" ,        defaultValue = "0",        required = false)   int numeroPagina,
            @RequestParam(value = "tamanhoDaPagina",        defaultValue = "10",       required = false)   int tamanhoPagina
            ){



        return  ResponseEntity.ok( this.medicoService.verTodosMedicos(ordenarPelo, ordenarDeForma, numeroPagina, tamanhoPagina) );
    }

    @PutMapping
    public ResponseEntity<?> atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dadosAtualizarMedico){

        return ResponseEntity.ok(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMedico(@PathVariable(name = "id") Long id){

       this.medicoService.deletarMedicoPeloId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> disableMedico(@PathVariable Long id){

        this.medicoService.inativarMedicoPeloId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Disabled!");
    }

}
