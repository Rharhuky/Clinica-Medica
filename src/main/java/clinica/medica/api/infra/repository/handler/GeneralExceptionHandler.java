package clinica.medica.api.infra.repository.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(){

        return ResponseEntity.notFound().build();

    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
//
//        var errors = ex.getFieldErrors();
//
////        Map<String, String > errorsResponse = new HashMap<>();
////        errors.forEach((erro) ->{
////            errorsResponse.put(erro.getField().toString(), erro.ge)
////        });
//        System.out.println(errors.get(0));
//
//        return ResponseEntity.badRequest().build();
//    }


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

        return ResponseEntity.badRequest().body(new ErrorCustomResponse(request.getDescription(false)  , errorsResponse, LocalTime.now()));
    }

    public record ErrorCustomResponse(String path, Map<String, String> detalhes, LocalTime localTime){

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
