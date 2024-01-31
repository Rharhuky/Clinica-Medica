package clinica.medica.api.service.interfaces;

import clinica.medica.api.model.dto.DadosAtualizarMedico;
import clinica.medica.api.model.dto.DadosListagemMedicos;
import clinica.medica.api.model.dto.MedicoDTO;

import java.util.List;

public interface MedicoService {

    MedicoDTO salvarMedico(MedicoDTO medicoDTO);

    List<DadosListagemMedicos> verTodosMedicos(String ordenarPelo, String ordenarDeForma, int numeroPagina, int tamanhoPagina);

    MedicoDTO verMedico(Long id);

    void atualizarDadosMedico(DadosAtualizarMedico dadosAtualizarMedico);

    void deletarMedicoPeloId(Long id);

    void inativarMedicoPeloId(Long id);

}
