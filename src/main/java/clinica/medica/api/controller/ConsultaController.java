package clinica.medica.api.controller;

import clinica.medica.api.model.dto.DadosAgendamentoConsulta;
import clinica.medica.api.model.dto.DetalhesConsulta;
import clinica.medica.api.service.ConsultaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "consultas")
public class ConsultaController {

    private ConsultaServiceImpl consultaService;

    public ConsultaController(ConsultaServiceImpl consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<?> criarConsulta(@RequestBody @Valid DadosAgendamentoConsulta agendamentoConsulta){
        System.out.println(agendamentoConsulta + " ---------");
        return ResponseEntity.ok(new DetalhesConsulta(null, null, null, null));
    }

}
