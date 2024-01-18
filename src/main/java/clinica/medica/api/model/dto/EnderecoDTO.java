package clinica.medica.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnderecoDTO{

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String numero;


}

