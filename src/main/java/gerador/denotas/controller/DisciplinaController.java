package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.Disciplina;
import gerador.denotas.service.DisciplinaService;
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
 * CRUD REST de disciplinas.
 *
 * <p>Expõe endpoints JSON em {@code /api/disciplinas}. Ex.: {@code {"nome": "Matemática", "cargaHoraria": 80}}.</p>
 */
@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

	private final DisciplinaService disciplinaService;

	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}

	@GetMapping
	public List<Disciplina> listar() {
		return disciplinaService.listar();
	}

	@GetMapping("/{id}")
	public Disciplina buscar(@PathVariable Long id) {
		return disciplinaService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Disciplina criar(@RequestBody Disciplina disciplina) {
		disciplina.setId(null);
		return disciplinaService.criar(disciplina);
	}

	@PutMapping("/{id}")
	public Disciplina atualizar(@PathVariable Long id, @RequestBody Disciplina disciplina) {
		return disciplinaService.atualizar(disciplina, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		disciplinaService.excluir(id);
	}

}
