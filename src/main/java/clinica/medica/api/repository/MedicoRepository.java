package clinica.medica.api.repository;

import clinica.medica.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCpf(String cpf);

    @Modifying
    @Query(value = " UPDATE medicos SET ativo=false WHERE medico_id=:id ", nativeQuery = true)
    void disableMedicoById(@Param(value = "id") Long id);

}
