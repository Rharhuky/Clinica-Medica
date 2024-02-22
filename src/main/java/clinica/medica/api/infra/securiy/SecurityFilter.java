package clinica.medica.api.infra.securiy;

import clinica.medica.api.usuario.UsuarioRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{

        var tokenJWT = extrairJWT(request);

        try{


            if(tokenJWT != null){
                var subject = tokenService.getSubject(tokenJWT); // pode estar válido ou não
                var usuario = usuarioRepository.findByLogin(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response); // próximo filtro ( continua o fluxo da requisicao ) ;
        }
        catch (Exception exception){

            DetalhesErro detalhesErro = new DetalhesErro();
            detalhesErro.setCode(HttpStatus.BAD_REQUEST);
            detalhesErro.setMessage(exception.getMessage());
            detalhesErro.setDetails(request.getPathInfo());

            response.setStatus(detalhesErro.getCode().value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(detalhesErro));
        }

    }

    private String extrairJWT(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null)
            return authorizationHeader.replace("Bearer ", "");

        return null;
    }

    @Setter
    @Getter
    private static class DetalhesErro{

        HttpStatus code;
        String message;
        String details;

    }

}
