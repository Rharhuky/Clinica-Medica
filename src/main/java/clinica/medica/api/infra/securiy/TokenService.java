package clinica.medica.api.infra.securiy;

import clinica.medica.api.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Lógica de programação de um token
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String token(Usuario usuario){
        try{

            Algorithm algorithm = Algorithm.HMAC256(secretKey); // parametro : string (chave secreta)
            String theToken = JWT.create()
                    .withIssuer("clinica-medica") //issuer : ferramenta responsável por gerar o token
                    .withSubject(usuario.getLogin()) // quem é o usuário, ou seja, relacionada ao token? (stateless) - dentro do token deve haver uma forma de identificar o user
                    .withClaim("id", usuario.getId()) // especificacoes do usuario
                    .withClaim("Data geracao", new Date())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
            return theToken;
        }
        catch(JWTCreationException ex){
            throw new RuntimeException("Erro na criação de token JWT", ex);
        }

    }

    public String getSubject(String tokenJWT) throws JWTVerificationException{


        var algoritmo = Algorithm.HMAC256(secretKey);
        return JWT.require(algoritmo)
                .withIssuer("clinica-medica")
                .build()
                .verify(tokenJWT)
                .getSubject();

    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // expira em 2 horas - fuso do brasil
    }

}
