package gerador.denotas.config;

import gerador.denotas.entity.Aluno;
import gerador.denotas.entity.Turma;
import gerador.denotas.repository.AlunoRepository;
import gerador.denotas.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Popula dados de desenvolvimento para permitir o login no portal.
 *
 * <p>Cria uma turma e um aluno de teste com as credenciais exibidas na página de
 * login (matrícula {@code 202610928} / senha {@code aluno123}).</p>
 */
@Component
public class DataInitializer implements CommandLineRunner {

	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;
	private final PasswordEncoder passwordEncoder;

	public DataInitializer(TurmaRepository turmaRepository,
			AlunoRepository alunoRepository,
			PasswordEncoder passwordEncoder) {
		this.turmaRepository = turmaRepository;
		this.alunoRepository = alunoRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void run(String... args) {
		if (alunoRepository.findByMatricula("202610928").isPresent()) {
			return;
		}

		Turma turma = new Turma();
		turma.setNome("1º Ano A");
		turma.setAnoLetivo(2026);
		turma = turmaRepository.save(turma);

		Aluno aluno = new Aluno();
		aluno.setTurma(turma);
		aluno.setNome("Lucas");
		aluno.setSobrenome("Silva");
		aluno.setMatricula("202610928");
		aluno.setSenhaHash(passwordEncoder.encode("aluno123"));
		alunoRepository.save(aluno);
	}

}
