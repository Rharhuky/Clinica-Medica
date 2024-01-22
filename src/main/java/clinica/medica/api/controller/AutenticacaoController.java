package clinica.medica.api.controller;

import clinica.medica.api.infra.securiy.DadosTokenJWT;
import clinica.medica.api.infra.securiy.TokenService;
import clinica.medica.api.usuario.DadosAutenticacao;
import clinica.medica.api.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    /**
     * DIipara o processo de autenticacao
     */
    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


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
//        var authentication = this.authenticationManager.authenticate(token);
//
//
//
//        return ResponseEntity.ok(tokenService.token((Usuario) authentication.getPrincipal()));

        var authentication = this.authenticationManager.authenticate(token);
        var tokenJWT = tokenService.token((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT)); // o token precisa armazenar o token

    }

}

