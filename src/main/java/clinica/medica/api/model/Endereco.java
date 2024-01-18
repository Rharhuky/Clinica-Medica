package clinica.medica.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FIXME by using @CEP annotation
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String numero;

}
