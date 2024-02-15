package clinica.medica.api.infra.handler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> handleValidationException(ValidacaoException exception, WebRequest request){

        SimpleErrorCustomResponse errorCustomResponse =
                new SimpleErrorCustomResponse(request.getDescription(false), exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCustomResponse);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(){

        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MedicoNaoEncontradoException.class)
    public ResponseEntity<?> handleMedicoNaoEncontradoException(MedicoNaoEncontradoException exception, WebRequest request){

        SimpleErrorCustomResponse errorCustomResponse =
                new SimpleErrorCustomResponse(request.getDescription(false), exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCustomResponse);

    }

    @ExceptionHandler(PacienteNaoEncontradoException.class)
    public ResponseEntity<?> handlePacienteNaoEncontradoException(PacienteNaoEncontradoException exception, WebRequest request){

        SimpleErrorCustomResponse errorCustomResponse =
                new SimpleErrorCustomResponse(request.getDescription(false), exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCustomResponse);

    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<Object> handleJWTDecodeException(JWTDecodeException exception, WebRequest request){

        SimpleErrorCustomResponse simpleErrorCustomResponse = new SimpleErrorCustomResponse(request.getDescription(false), exception.getMessage(), LocalDateTime.now());
//        return ResponseEntity.badRequest().body(simpleErrorCustomResponse);
        return handleExceptionInternal(exception, simpleErrorCustomResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<SimpleErrorCustomResponse> handleJWTVerificationException(JWTVerificationException exception, WebRequest request){

        SimpleErrorCustomResponse errorCustomResponse = new SimpleErrorCustomResponse(request.getDescription(false), exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorCustomResponse);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errorsResponse = new HashMap<>();
        ex.getFieldErrors()
                .forEach((error) ->
                        errorsResponse.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(new ErrorCustomResponse(request.getDescription(false)  , errorsResponse, LocalDateTime.now()));
    }

    private record ErrorCustomResponse(String path, Map<String, String> detalhes, LocalDateTime localTime){

    }

    private record SimpleErrorCustomResponse(String path, String detalhes, LocalDateTime localTime){

    }

//    @ExceptionHandler(PSQLException.class)
//    public ResponseEntity<?> handlePSQLException(PSQLException ex, WebRequest request){
//
//        Map<String, String> errorsResponse = new HashMap<>();
////        ex.getServerErrorMessage();
//            ex.getServerErrorMessage().getConstraint();
//        return ResponseEntity.badRequest().body(ex.getServerErrorMessage().getConstraint());
//    }

//    @AllArgsConstructor
//    static class ErrorResponseDetails<T extends PSQLException>{
//
//        T objectError;
//        String path;
//
//
//        Map<String, String> getDetails(){
//
//            objectError.getMessage();
//        }
//
//    }

}
