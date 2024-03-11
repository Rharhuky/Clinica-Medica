package clinica.medica.api.infra.repository;

import clinica.medica.api.model.Consulta;
import clinica.medica.api.model.Medico;
import clinica.medica.api.model.Paciente;
import clinica.medica.api.model.Ramo;
import clinica.medica.api.model.dto.DadosRegistroPaciente;
import clinica.medica.api.model.dto.EnderecoDTO;
import clinica.medica.api.model.dto.MedicoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName(value = "Devolver empty quando medico cadastrado nao esta disponivel na data")
    void escolherMedicoDisponivel() {

        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var paciente = cadastrarPaciente("Paciente", "paciente@mail.com", "12221");
        var medico = cadastrarMedico("Medico", "medico@medico.mail", "12344", Ramo.CARDIOLOGIA);

        cadastrarConsulta(medico, paciente,proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoDisponivel(Ramo.CARDIOLOGIA.name(), proximaSegundaAs10);
        assertThat(medicoLivre).isEmpty();
    }

    @Test
    @DisplayName(value = "Devolver medico quando estiver disponível na data")
    void escolherMedicoDisponivel2() {

        var proximaTercaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).atTime(10, 0);
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);


        var paciente = cadastrarPaciente("Paciente", "paciente@mail.com", "12221");
        var medico = cadastrarMedico("Medico", "medico@medico.mail", "12344", Ramo.CARDIOLOGIA);
        cadastrarConsulta(medico, paciente,proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoDisponivel(Ramo.CARDIOLOGIA.name(), proximaTercaAs10);

        assertThat(medicoLivre).isPresent().isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        testEntityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Ramo especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade, "12345"));
        return testEntityManager.persist(medico);
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private MedicoDTO dadosMedico(String nome, String email, String crm, Ramo especialidade, String cpf) {
        return new MedicoDTO(
                null,
                nome,
                cpf,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosRegistroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosRegistroPaciente(
                null,
                nome,
                email,
                cpf,
                "61999999999",
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF"
        );
    }
}