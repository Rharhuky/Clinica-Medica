package clinica.medica.api.controller;

import clinica.medica.api.model.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Long id, // apenas este é obrigatório.
        String nome,
        String celular,
        EnderecoDTO endereco) {
}
