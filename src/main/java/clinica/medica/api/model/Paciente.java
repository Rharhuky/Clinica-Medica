package clinica.medica.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "pacientes", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf", "telefone", "email"})})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Consulta> consultas = new ArrayList<>();

//    public Paciente(DadosCadastroPaciente dados) {
//        this.ativo = true;
//        this.nome = dados.nome();
//        this.email = dados.email();
//        this.telefone = dados.telefone();
//        this.cpf = dados.cpf();
//        this.endereco = new Endereco(dados.endereco());
//    }
//
//    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
//        if (dados.nome() != null) {
//            this.nome = dados.nome();
//        }
//        if (dados.telefone() != null) {
//            this.telefone = dados.telefone();
//        }
//        if (dados.endereco() != null) {
//            this.endereco.atualizarInformacoes(dados.endereco());
//        }
//
//    }

    @PrePersist
    private void activePaciente(){
        this.ativo = true;
    }
}
