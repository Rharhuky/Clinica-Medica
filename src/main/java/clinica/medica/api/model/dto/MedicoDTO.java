package clinica.medica.api.model.dto;

import clinica.medica.api.model.Ramo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO{

    @NotNull
    private Long medicoId;

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String celular;

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    private String crm;

    @NotNull
    private Ramo ramo;

    @NotNull
    @Valid
    private EnderecoDTO endereco ;
}
