package gerador.denotas.controller;

import java.util.List;

import gerador.denotas.entity.Aluno;
import gerador.denotas.repository.AlunoRepository;
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
import org.springframework.web.server.ResponseStatusException;

/**
 * CRUD REST de alunos.
 *
 * <p>Expõe endpoints JSON em {@code /api/alunos}. Para criar/atualizar, a turma é
 * informada aninhada, ex.: {@code {"turma": {"id": 1}, "nome": "Lucas", ...}}.</p>
 */
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

	private final AlunoRepository alunoRepository;

	public AlunoController(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@GetMapping
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Aluno buscar(@PathVariable Long id) {
		return alunoRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado com o id " + id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno criar(@RequestBody Aluno aluno) {
		aluno.setId(null);
		return alunoRepository.save(aluno);
	}

	@PutMapping("/{id}")
	public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
		Aluno existente = alunoRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado com o id " + id));

		existente.setTurma(aluno.getTurma());
		existente.setNome(aluno.getNome());
		existente.setSobrenome(aluno.getSobrenome());
		existente.setMatricula(aluno.getMatricula());
		existente.setSenhaHash(aluno.getSenhaHash());
		existente.setDataNascimento(aluno.getDataNascimento());
		existente.setTelefone(aluno.getTelefone());
		existente.setEmail(aluno.getEmail());
		existente.setFotoUrl(aluno.getFotoUrl());
		existente.setNomePai(aluno.getNomePai());
		existente.setTelefonePai(aluno.getTelefonePai());
		existente.setNomeMae(aluno.getNomeMae());
		existente.setTelefoneMae(aluno.getTelefoneMae());

		return alunoRepository.save(existente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		if (!alunoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado com o id " + id);
		}
		alunoRepository.deleteById(id);
	}

}
