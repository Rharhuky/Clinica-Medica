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

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String numero;

}
