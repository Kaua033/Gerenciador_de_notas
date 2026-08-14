package gerador.denotas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import gerador.denotas.config.SecurityConfig;
import gerador.denotas.entity.Aluno;
import gerador.denotas.entity.Turma;
import gerador.denotas.repository.AlunoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Testes do CRUD REST de alunos.
 */
@WebMvcTest(AlunoController.class)
@Import(SecurityConfig.class)
class AlunoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private AlunoRepository alunoRepository;

	private Aluno aluno(Long id, String nome) {
		Turma turma = new Turma();
		turma.setId(1L);
		Aluno aluno = new Aluno();
		aluno.setId(id);
		aluno.setTurma(turma);
		aluno.setNome(nome);
		aluno.setSobrenome("Silva");
		aluno.setMatricula("202610928");
		aluno.setSenhaHash("hash");
		return aluno;
	}

	private static String corpoJson() {
		return """
			{"turma": {"id": 1}, "nome": "Lucas", "sobrenome": "Silva",
			 "matricula": "202610928", "senhaHash": "hash"}
			""";
	}

	@Test
	@WithMockUser
	void listarRetornaTodosOsAlunos() throws Exception {
		when(alunoRepository.findAll()).thenReturn(List.of(aluno(1L, "Lucas")));

		mockMvc.perform(get("/api/alunos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].nome").value("Lucas"))
			.andExpect(jsonPath("$[0].turma.id").value(1));
	}

	@Test
	@WithMockUser
	void buscarRetornaAlunoQuandoExiste() throws Exception {
		when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno(1L, "Lucas")));

		mockMvc.perform(get("/api/alunos/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.matricula").value("202610928"));
	}

	@Test
	@WithMockUser
	void buscarRetorna404QuandoNaoExiste() throws Exception {
		when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/alunos/99"))
			.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser
	void criarRetorna201() throws Exception {
		when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno(1L, "Lucas"));

		mockMvc.perform(post("/api/alunos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(corpoJson())
				.with(csrf()))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser
	void criarSemTokenCsrfFuncionaNaApi() throws Exception {
		when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno(1L, "Lucas"));

		mockMvc.perform(post("/api/alunos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(corpoJson()))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	@WithMockUser
	void atualizarRetorna200() throws Exception {
		when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno(1L, "Lucas")));
		when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(put("/api/alunos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{"turma": {"id": 1}, "nome": "Lucas", "sobrenome": "Silva",
					 "matricula": "202610928", "senhaHash": "hash", "telefone": "11999999999"}
					""")
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.telefone").value("11999999999"));
	}

	@Test
	@WithMockUser
	void atualizarRetorna404QuandoNaoExiste() throws Exception {
		when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

		mockMvc.perform(put("/api/alunos/99")
				.contentType(MediaType.APPLICATION_JSON)
				.content(corpoJson())
				.with(csrf()))
			.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser
	void excluirRetorna204() throws Exception {
		when(alunoRepository.existsById(1L)).thenReturn(true);

		mockMvc.perform(delete("/api/alunos/1").with(csrf()))
			.andExpect(status().isNoContent());

		verify(alunoRepository).deleteById(1L);
	}

	@Test
	@WithMockUser
	void excluirRetorna404QuandoNaoExiste() throws Exception {
		when(alunoRepository.existsById(99L)).thenReturn(false);

		mockMvc.perform(delete("/api/alunos/99").with(csrf()))
			.andExpect(status().isNotFound());
	}

}
