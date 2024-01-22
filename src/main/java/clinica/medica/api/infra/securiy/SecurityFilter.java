package clinica.medica.api.infra.securiy;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * O spring garante que, nessa classe que se herda, será executada uma única vez p/ cada requisição.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {
    /**
     *
     * @param request
     * @param response
     * @param filterChain : cadeia de filtros existentes na aplicação, no caso, Clínica médica
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Recuperar o token ( como vai chegar na requisicao ? pelo Header :D - Authorization )

        var tokenJWT = extrairJWT(request);
        filterChain.doFilter(request, response); // próximo filtro ( continua o fluxo da requisicao ) ;


    }

    private String extrairJWT(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null)
            throw new RuntimeException("Token JWT não enviado");

        return authorizationHeader.replace("Bearer ", "");

    }
}
