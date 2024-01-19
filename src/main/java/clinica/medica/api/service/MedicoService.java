package clinica.medica.api.service;

import clinica.medica.api.model.dto.DadosListagemMedicos;
import clinica.medica.api.model.dto.MedicoDTO;

import java.util.List;

public interface MedicoService {

    void salvarMedico(MedicoDTO medicoDTO);

    List<DadosListagemMedicos> verTodosMedicos(String ordenarPelo, String ordenarDeForma, int numeroPagina, int tamanhoPagina);



}
