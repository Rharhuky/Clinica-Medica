package clinica.medica.api.repository;

import clinica.medica.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCpf(String cpf);

}
