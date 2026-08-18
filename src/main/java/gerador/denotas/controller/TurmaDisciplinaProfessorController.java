package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.TurmaDisciplinaProfessor;
import gerador.denotas.service.TurmaDisciplinaProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD REST de vínculos turma/disciplina/professor.
 *
 * <p>Expõe endpoints JSON em {@code /api/turmas-disciplinas-professores}. Para
 * criar/atualizar, as entidades relacionadas são informadas aninhadas, ex.:
 * {@code {"turma": {"id": 1}, "disciplina": {"id": 2}, "professor": {"id": 3}, "anoLetivo": 2026}}.</p>
 */
@RestController
@RequestMapping("/api/turmas-disciplinas-professores")
public class TurmaDisciplinaProfessorController {

	private final TurmaDisciplinaProfessorService turmaDisciplinaProfessorService;

	public TurmaDisciplinaProfessorController(TurmaDisciplinaProfessorService turmaDisciplinaProfessorService) {
		this.turmaDisciplinaProfessorService = turmaDisciplinaProfessorService;
	}

	@GetMapping
	public List<TurmaDisciplinaProfessor> listar() {
		return turmaDisciplinaProfessorService.listar();
	}

	@GetMapping("/{id}")
	public TurmaDisciplinaProfessor buscar(@PathVariable Long id) {
		return turmaDisciplinaProfessorService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TurmaDisciplinaProfessor criar(@RequestBody TurmaDisciplinaProfessor turmaDisciplinaProfessor) {
		turmaDisciplinaProfessor.setId(null);
		return turmaDisciplinaProfessorService.criar(turmaDisciplinaProfessor);
	}

	@PutMapping("/{id}")
	public TurmaDisciplinaProfessor atualizar(@PathVariable Long id, @RequestBody TurmaDisciplinaProfessor turmaDisciplinaProfessor) {
		return turmaDisciplinaProfessorService.atualizar(turmaDisciplinaProfessor, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		turmaDisciplinaProfessorService.excluir(id);
	}

}
