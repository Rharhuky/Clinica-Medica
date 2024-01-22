package clinica.medica.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnderecoDTO{

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    //@Cep
    @Pattern(regexp = "\\d{8}")
    private String cep;

    @NotBlank
    private String cidade;


    private String numero;


}

