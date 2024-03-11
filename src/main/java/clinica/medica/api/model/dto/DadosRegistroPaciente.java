package clinica.medica.api.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DadosRegistroPaciente{

    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;
    @NotNull
    @Valid
    private EnderecoDTO endereco;

}
