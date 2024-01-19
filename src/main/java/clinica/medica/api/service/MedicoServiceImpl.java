package clinica.medica.api.service;

import clinica.medica.api.model.Medico;
import clinica.medica.api.model.dto.MedicoDTO;
import clinica.medica.api.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public List<MedicoDTO> verTodosMedicos() {

        return this.medicoRepository.findAll().
                stream().
                map((medico -> modelMapper.map(medico, MedicoDTO.class) )).toList();
    }

/*
    private MedicoDTO mapToMedicoDTO(Medico medico){
        return this.modelMapper.map(medico, MedicoDTO.class);
    }
    */

}
