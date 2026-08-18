package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.Turma;
import gerador.denotas.service.TurmaService;
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
 * CRUD REST de turmas.
 *
 * <p>Expõe endpoints JSON em {@code /api/turmas}. Ex.: {@code {"nome": "1º Ano A", "anoLetivo": 2026}}.</p>
 */
@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

	private final TurmaService turmaService;

	public TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}

	@GetMapping
	public List<Turma> listar() {
		return turmaService.listar();
	}

	@GetMapping("/{id}")
	public Turma buscar(@PathVariable Long id) {
		return turmaService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Turma criar(@RequestBody Turma turma) {
		turma.setId(null);
		return turmaService.criar(turma);
	}

	@PutMapping("/{id}")
	public Turma atualizar(@PathVariable Long id, @RequestBody Turma turma) {
		return turmaService.atualizar(turma, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		turmaService.excluir(id);
	}

}
