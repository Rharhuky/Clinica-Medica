package clinica.medica.api.controller;

import clinica.medica.api.usuario.DadosAutenticacao;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    /**
     * DIipara o processo de autenticacao
     */
    private final AuthenticationManager authenticationManager;

    public AutenticacaoController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * @param dadosAutenticacao dto to send login and password, bro.
     * @return
     */
    @PostMapping
    public ResponseEntity<?> login(@RequestBody DadosAutenticacao dadosAutenticacao){
        System.out.println(new BCryptPasswordEncoder().encode(dadosAutenticacao.senha()));
        System.out.println(dadosAutenticacao.senha());

        // classe que representa usuario e senha.
        // é um dto do spring. ( usernamePassowrd...)
        var token = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());

        // O authenticate devolve um Objeto que representa o usuário autenticado no sistema.
        var authentication = this.authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}


















