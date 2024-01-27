package clinica.medica.api.controller;

import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DadosCancelamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;
import clinica.medica.api.service.ConsultaServiceImpl;
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
    public ResponseEntity<?> criarConsulta(@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta){
        var consulta = this.consultaService.agendarConsulta(agendamentoConsulta);
        return new ResponseEntity<>(consulta, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta){

        this.consultaService.cancelarConsulta(dadosCancelamentoConsulta);
        return ResponseEntity.notFound().build();

    }


}
