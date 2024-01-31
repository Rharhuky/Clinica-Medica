package clinica.medica.api.controller;

import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;
import clinica.medica.api.service.impl.ConsultaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "consultas")
public class ConsultaController {

    private ConsultaServiceImpl consultaService;

    public ConsultaController(ConsultaServiceImpl consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<DetalhesConsulta> criar(@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta){
        var consulta = this.consultaService.agendarConsulta(agendamentoConsulta);
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> get(){
        var consultas = this.consultaService.getAll();
        return ResponseEntity.ok(consultas);
    }

    @DeleteMapping
    public ResponseEntity<?> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta){

        this.consultaService.cancelarConsulta(dadosCancelamentoConsulta);
        return ResponseEntity.notFound().build();

    }

}
