package clinica.medica.api.infra.securiy;

import clinica.medica.api.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    /**
     * Geração do token, utiliza-se a biblioteca adicionada(auth0).
     * @return
     */
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

    /**
     * Recuperacoa de Subject ( descobrir quem está disparando a requisicao, basicamente.
     * @param tokenJWT : o token enviado no cabecalho HTTP
     * @return o subject
     */
    public String getSubject(String tokenJWT){
//        return JWT.decode(tokenJWT).getSubject();
//        System.out.println(JWT.decode(tokenJWT).getSubject()); // aparentemente funciona ...

        try{

            var algoritmo = Algorithm.HMAC256(secretKey);
            return JWT.require(algoritmo)
                    .withIssuer("clinica-medica")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }

        catch(JWTVerificationException ex){
            throw new RuntimeException("Token JWT inválido");
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // expira em 2 horas - fuso do brasil
    }

}
