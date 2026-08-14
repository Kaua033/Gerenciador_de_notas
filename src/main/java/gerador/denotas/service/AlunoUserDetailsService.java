package gerador.denotas.service;

import gerador.denotas.entity.Aluno;
import gerador.denotas.repository.AlunoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço de autenticação que carrega o aluno pela <em>matrícula</em>,
 * permitindo o login com matrícula + senha.
 */
@Service
public class AlunoUserDetailsService implements UserDetailsService {

	private final AlunoRepository alunoRepository;

	public AlunoUserDetailsService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		Aluno aluno = alunoRepository.findByMatricula(matricula)
			.orElseThrow(() -> new UsernameNotFoundException("Aluno não encontrado para a matrícula: " + matricula));

		return User.builder()
			.username(aluno.getMatricula())
			.password(aluno.getSenhaHash())
			.roles("ALUNO")
			.build();
	}

}
