package clinica.medica.api.model;

import clinica.medica.api.model.dto.MedicoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicoId;

    private String nome;

    private String cpf;

    private String crm;

    private String celular;

    private boolean ativo;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Ramo ramo;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "medico", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Consulta> consultas = new ArrayList<>();

    public Medico(MedicoDTO medicoDTO) {
        this.ramo = medicoDTO.getRamo();
        this.endereco = new Endereco(medicoDTO.getEndereco());
        this.crm = medicoDTO.getCrm();
        this.nome = medicoDTO.getNome();
        this.email = medicoDTO.getEmail();
        this.cpf = medicoDTO.getCpf();
    }

    @PrePersist
    private void setUp(){
        this.ativo = true;
    }

}
