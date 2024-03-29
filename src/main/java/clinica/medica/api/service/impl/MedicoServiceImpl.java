package clinica.medica.api.service.impl;

import clinica.medica.api.model.dto.DadosAtualizarMedico;
import clinica.medica.api.model.Endereco;
import clinica.medica.api.model.Medico;
import clinica.medica.api.model.dto.DadosListagemMedicos;
import clinica.medica.api.model.dto.MedicoDTO;
import clinica.medica.api.infra.repository.MedicoRepository;
import clinica.medica.api.service.interfaces.MedicoService;
import jakarta.persistence.EntityNotFoundException;
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
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public MedicoDTO verMedico(Long id) {
        return  modelMapper.map(this.medicoRepository.findById(id).orElseThrow(EntityNotFoundException::new), MedicoDTO.class);
    }

    @Override
    @Transactional
    public MedicoDTO salvarMedico(MedicoDTO medicoDTO) {

        Medico novoMedico = this.modelMapper.map(medicoDTO, Medico.class);
        novoMedico = this.medicoRepository.save(novoMedico);
        return modelMapper.map(novoMedico, MedicoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosListagemMedicos> verTodosMedicos(String ordenarPelo, String ordenarDeForma, int numeroPagina, int tamanhoPagina) {
        Sort sortBy =
                ordenarDeForma.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(ordenarPelo).ascending()
                        : Sort.by(ordenarPelo).descending();

        Pageable paginacaoCustomizada = PageRequest.of(numeroPagina, tamanhoPagina, sortBy);

        return this.medicoRepository.findAll(paginacaoCustomizada)
                .stream().
                map((medico -> new DadosListagemMedicos(
                        medico.getMedicoId(),
                        medico.getNome(),
                        medico.getEmail(),
                        medico.getCrm(),
                        medico.getRamo()))).toList();

    }

    @Override
    @Transactional
    public void atualizarDadosMedico(DadosAtualizarMedico dadosAtualizarMedico) {

        Medico medico = this.medicoRepository.findById(dadosAtualizarMedico.id()).orElseThrow(RuntimeException::new);

        if(dadosAtualizarMedico.nome() != null)
            medico.setNome(dadosAtualizarMedico.nome());

        if(dadosAtualizarMedico.celular() != null)
            medico.setCelular(dadosAtualizarMedico.celular());

        if(dadosAtualizarMedico.endereco() != null)
            medico.setEndereco(modelMapper.map(dadosAtualizarMedico.endereco(), Endereco.class));


    }

    @Override
    @Transactional
    public void deletarMedicoPeloId(Long id) {
        Medico theMedico = this.medicoRepository.findById(id).orElseThrow(RuntimeException::new);
        this.medicoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void inativarMedicoPeloId(Long id) {

        Medico theMedico = this.medicoRepository.findById(id).orElseThrow(RuntimeException::new);
        this.medicoRepository.disableMedicoById(id);

    }
}
