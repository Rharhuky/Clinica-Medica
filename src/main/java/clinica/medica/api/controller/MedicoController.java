package clinica.medica.api.controller;

import clinica.medica.api.model.Medico;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "medicos")
public class MedicoController {

    @RequestMapping(method = RequestMethod.GET)
    public String cadastrarMedico(@RequestBody Medico novoMedico){

        return String.format("Médico %s cadastrado com <strong>sucesso</strong>", novoMedico.getNome());
    }

}
