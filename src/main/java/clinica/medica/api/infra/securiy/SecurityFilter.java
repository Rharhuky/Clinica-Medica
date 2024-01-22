package clinica.medica.api.infra.securiy;

import clinica.medica.api.service.MedicoService;
import clinica.medica.api.usuario.UsuarioRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * O spring garante que, nessa classe que se herda, será executada uma única vez p/ cada requisição.
 */

@RequiredArgsConstructor

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

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

            if(tokenJWT != null){
                var subject = tokenService.getSubject(tokenJWT); // pode estar válido ou não
                var usuario = usuarioRepository.findByLogin(subject);
                System.out.println("Subject: " + subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }


            filterChain.doFilter(request, response); // próximo filtro ( continua o fluxo da requisicao ) ;


    }

    private String extrairJWT(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null)
            return authorizationHeader.replace("Bearer ", "");

        return null;
    }
}
