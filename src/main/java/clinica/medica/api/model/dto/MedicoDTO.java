package clinica.medica.api.model.dto;

import clinica.medica.api.model.Ramo;

public record MedicoDTO(String nome, String email, String celular, String crm, Ramo ramo, EnderecoDTO enderecoDTO) {
}
