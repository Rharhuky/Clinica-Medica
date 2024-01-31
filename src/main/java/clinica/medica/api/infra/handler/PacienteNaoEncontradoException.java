package clinica.medica.api.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PacienteNaoEncontradoException extends RuntimeException{

    public PacienteNaoEncontradoException(String message) {
        super(message);
    }

    public PacienteNaoEncontradoException() {
        super("Paciente não encontrado");
    }
}
