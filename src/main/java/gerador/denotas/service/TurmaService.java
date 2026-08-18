package gerador.denotas.service;

import java.util.List;

import gerador.denotas.entity.Turma;
import gerador.denotas.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;

	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}

	public Turma criar(Turma turma) {
		return turmaRepository.save(turma);
	}

	public List<Turma> listar() {
		return turmaRepository.findAll();
	}

	public Turma buscar(Long id) {
		return turmaRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada com o id " + id));
	}

	public void excluir(Long id) {
		if (!turmaRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada com o id " + id);
		}
		turmaRepository.deleteById(id);
	}

	public Turma atualizar(Turma turma, Long id) {
		Turma existente = buscar(id);

		existente.setNome(turma.getNome());
		existente.setAnoLetivo(turma.getAnoLetivo());

		return turmaRepository.save(existente);
	}
}
