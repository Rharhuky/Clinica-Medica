package clinica.medica.api.infra.repository;

import clinica.medica.api.model.Medico;
import clinica.medica.api.model.Ramo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCpf(String cpf);

    @Modifying
    @Query(value = " UPDATE medicos SET ativo=false WHERE medico_id=:id ", nativeQuery = true)
    void disableMedicoById(@Param(value = "id") Long id);

    @Query(value = """
                SELECT * FROM medicos WHERE ativo=true
                AND ramo=:ramo
                AND NOT EXISTS(
                    SELECT * FROM consultas
                    WHERE consultas.data=:data
                    AND motivo_cancelamento is null
                )
                ORDER BY random() LIMIT 1
            """, nativeQuery = true)
    Optional<Medico> escolherMedicoDisponivel(@Param(value = "ramo") String ramo, @Param(value = "data") LocalDateTime data);


    @Query(value = """
           SELECT ativo FROM medicos
           WHERE medico_id=:id
            """,
            nativeQuery = true)
    Boolean medicoIsAtivo(@Param(value = "id") Long id);
}

/**
 *
 * SELECT * FROM medicos WHERE ativo='true'
 * AND ramo='cardiologia'
 * AND NOT EXISTS(
 * SELECT * FROM consultas
 * WHERE consultas.data='2022-11-10 10:22'
 *  )
 * ORDER BY random() LIMIT 1
 *
 */

