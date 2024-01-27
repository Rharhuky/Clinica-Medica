package clinica.medica.api.infra.repository;

import clinica.medica.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(value = "SELECT ativo FROM pacientes WHERE id=:id", nativeQuery = true)
    Boolean findAtivoById(Long id);
}
