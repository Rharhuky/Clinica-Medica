package clinica.medica.api.config;

import clinica.medica.api.model.Endereco;
import clinica.medica.api.model.Medico;
import clinica.medica.api.model.dto.EnderecoDTO;
import clinica.medica.api.model.dto.MedicoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Configuracao implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5050") // ENDERECO aplicacao front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapperEndereco = new ModelMapper();
        modelMapperEndereco.addMappings(new PropertyMap<EnderecoDTO, Endereco>() {
            @Override
            protected void configure() {

                map().setBairro(source.getBairro());
                map().setCep(source.getCep());
                map().setCidade(source.getCidade());
                map().setLogradouro(source.getLogradouro());
                map().setNumero(source.getNumero());

            }
        });

        modelMapperEndereco.addMappings(new PropertyMap<MedicoDTO, Medico>() {
            /**
             * por algum motivo nao funciona com record
             */
            @Override
            protected void configure() {

                map().setCpf(source.getCpf());
                map().setCrm(source.getCrm());
                map().setNome(source.getNome());
                map().setEmail(source.getEmail());
                map().setRamo(source.getRamo());

            }
        });

        return modelMapperEndereco;
    }

}
