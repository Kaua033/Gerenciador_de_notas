package gerador.denotas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import gerador.denotas.entity.Aluno;
import gerador.denotas.repository.AlunoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Testes do serviço de autenticação por matrícula.
 */
@ExtendWith(MockitoExtension.class)
class AlunoUserDetailsServiceTest {

	@Mock
	private AlunoRepository alunoRepository;

	private AlunoUserDetailsService userDetailsService;

	@BeforeEach
	void setUp() {
		userDetailsService = new AlunoUserDetailsService(alunoRepository);
	}

	@Test
	void carregaAlunoPelaMatricula() {
		Aluno aluno = new Aluno();
		aluno.setMatricula("202610928");
		aluno.setSenhaHash("$2a$10$hashTeste");

		when(alunoRepository.findByMatricula("202610928")).thenReturn(Optional.of(aluno));

		UserDetails user = userDetailsService.loadUserByUsername("202610928");

		assertEquals("202610928", user.getUsername());
		assertEquals("$2a$10$hashTeste", user.getPassword());
		assertTrue(user.getAuthorities().stream()
			.anyMatch(a -> a.getAuthority().equals("ROLE_ALUNO")));
	}

	@Test
	void matriculaInexistenteLancaExcecao() {
		when(alunoRepository.findByMatricula("999999")).thenReturn(Optional.empty());

		assertThrows(UsernameNotFoundException.class,
			() -> userDetailsService.loadUserByUsername("999999"));
	}

}
