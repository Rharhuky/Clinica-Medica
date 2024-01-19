package clinica.medica.api.service;

import clinica.medica.api.model.Medico;
import clinica.medica.api.model.dto.DadosListagemMedicos;
import clinica.medica.api.model.dto.MedicoDTO;
import clinica.medica.api.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor

@Service
public class MedicoServiceImpl implements MedicoService{

    private final MedicoRepository medicoRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public void salvarMedico(MedicoDTO medicoDTO) {

        Medico novoMedico = this.modelMapper.map(medicoDTO, Medico.class);
        System.out.println(novoMedico);
        this.medicoRepository.save(novoMedico);

    }

    @Override
    public List<DadosListagemMedicos> verTodosMedicos(String ordenarPelo, String ordenarDeForma, int numeroPagina, int tamanhoPagina) {
        Sort sortBy =
                ordenarDeForma.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(ordenarPelo).ascending()
                        : Sort.by(ordenarPelo).descending();

        Pageable paginacaoCustomizada = PageRequest.of(numeroPagina, tamanhoPagina, sortBy);

        return this.medicoRepository.findAll(paginacaoCustomizada)
                .stream().
                map((medico -> new DadosListagemMedicos(medico.getNome(),
                        medico.getEmail(),
                        medico.getCrm(),
                        medico.getRamo()))).toList();

    }

}
