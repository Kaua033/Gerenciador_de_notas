package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.Nota;
import gerador.denotas.service.NotaService;
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
 * CRUD REST de notas.
 *
 * <p>Expõe endpoints JSON em {@code /api/notas}. Ex.: {@code {"aluno": {...}, "disciplina": {...}, "unidade": {...}, "valor": 8.5}}</p>
 */
@RestController
@RequestMapping("/api/notas")
public class NotaController {

	private final NotaService notaService;

	public NotaController(NotaService notaService) {
		this.notaService = notaService;
	}

	@GetMapping
	public List<Nota> listar() {
		return notaService.listar();
	}

	@GetMapping("/{id}")
	public Nota buscar(@PathVariable Long id) {
		return notaService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Nota criar(@RequestBody Nota nota) {
		nota.setId(null);
		return notaService.criar(nota);
	}

	@PutMapping("/{id}")
	public Nota atualizar(@PathVariable Long id, @RequestBody Nota nota) {
		return notaService.atualizar(nota, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		notaService.excluir(id);
	}

}
