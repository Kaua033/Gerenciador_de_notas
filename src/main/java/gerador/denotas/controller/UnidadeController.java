package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.Unidade;
import gerador.denotas.service.UnidadeService;
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
 * CRUD REST de unidades.
 *
 * <p>Expõe endpoints JSON em {@code /api/unidades}. Ex.: {@code {"nome": "1ª Unidade", "ordem": 1, "anoLetivo": 2026}}.</p>
 */
@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

	private final UnidadeService unidadeService;

	public UnidadeController(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}

	@GetMapping
	public List<Unidade> listar() {
		return unidadeService.listar();
	}

	@GetMapping("/{id}")
	public Unidade buscar(@PathVariable Long id) {
		return unidadeService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Unidade criar(@RequestBody Unidade unidade) {
		unidade.setId(null);
		return unidadeService.criar(unidade);
	}

	@PutMapping("/{id}")
	public Unidade atualizar(@PathVariable Long id, @RequestBody Unidade unidade) {
		return unidadeService.atualizar(unidade, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		unidadeService.excluir(id);
	}

}
