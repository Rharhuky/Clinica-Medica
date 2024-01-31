package clinica.medica.api.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicoNaoEncontradoException extends RuntimeException {

    public MedicoNaoEncontradoException() {
        super("Médico n]ao encontrado");
    }

    public MedicoNaoEncontradoException(String message) {
        super(message);
    }
}
