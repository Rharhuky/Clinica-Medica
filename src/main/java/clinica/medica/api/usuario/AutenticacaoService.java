package clinica.medica.api.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Lógica de autenticação do projeto
 */

@RequiredArgsConstructor

@Service
public class AutenticacaoService implements UserDetailsService {


    private UsuarioRepository usuarioRepository;

    /**
     *
     * @param username the username identifying the user whose data is required. - (login: name or email etc)
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
}
