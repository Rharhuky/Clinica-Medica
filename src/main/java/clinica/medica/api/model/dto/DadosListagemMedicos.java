package clinica.medica.api.model.dto;

import clinica.medica.api.model.Ramo;

public record DadosListagemMedicos(Long id, String nome, String email, String crm, Ramo ramo) {

    public DadosListagemMedicos(Long id, String nome, String email, String crm, Ramo ramo) {
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.ramo = ramo;
        this.id = id;
    }
}
