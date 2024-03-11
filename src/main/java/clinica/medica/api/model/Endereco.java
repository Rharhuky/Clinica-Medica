package clinica.medica.api.model;

import clinica.medica.api.model.dto.EnderecoDTO;
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


    public Endereco(EnderecoDTO endereco) {
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.numero = endereco.getNumero();
    }
}
