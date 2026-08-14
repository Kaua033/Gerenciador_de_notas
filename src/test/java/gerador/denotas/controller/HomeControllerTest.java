package gerador.denotas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import gerador.denotas.config.SecurityConfig;
import gerador.denotas.entity.Aluno;
import gerador.denotas.repository.AlunoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Testes do controller de páginas da aplicação.
 */
@WebMvcTest(HomeController.class)
@Import(SecurityConfig.class)
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private AlunoRepository alunoRepository;

	@Test
	void indexSemAutenticacaoRedirecionaParaLogin() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/login"));
	}

	@Test
	void loginRetornaPaginaDeLogin() throws Exception {
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"));
	}

	@Test
	@WithMockUser(username = "202610928", roles = "ALUNO")
	void indexAutenticadoRetornaTemplateComAtributos() throws Exception {
		Aluno aluno = new Aluno();
		aluno.setMatricula("202610928");
		aluno.setNome("Lucas");
		aluno.setSobrenome("Silva");
		when(alunoRepository.findByMatricula("202610928")).thenReturn(Optional.of(aluno));

		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"))
			.andExpect(model().attributeExists("titulo", "mensagem", "dataAtual", "aluno"));
	}

}
