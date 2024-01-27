package clinica.medica.api.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String message) {
        super(message);
    }
}
