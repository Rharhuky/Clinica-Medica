package clinica.medica.api.model.dto;

import clinica.medica.api.model.Ramo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDTO{
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private String crm;
    private Ramo ramo;
    private EnderecoDTO enderecoDTO ;
}
