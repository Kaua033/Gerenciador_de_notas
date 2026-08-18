package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.Professor;
import gerador.denotas.service.ProfessorService;
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
 * CRUD REST de professores.
 *
 * <p>Expõe endpoints JSON em {@code /api/professores}. Ex.: {@code {"nome": "Maria Silva", "registro": "REG-001"}}.</p>
 */
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@GetMapping
	public List<Professor> listar() {
		return professorService.listar();
	}

	@GetMapping("/{id}")
	public Professor buscar(@PathVariable Long id) {
		return professorService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Professor criar(@RequestBody Professor professor) {
		professor.setId(null);
		return professorService.criar(professor);
	}

	@PutMapping("/{id}")
	public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
		return professorService.atualizar(professor, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		professorService.excluir(id);
	}

}
