package gerador.denotas.service;

import gerador.denotas.entity.Disciplina;
import gerador.denotas.repository.DisciplinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DisciplinaService {

	private final DisciplinaRepository disciplinaRepository;

	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}

	public Disciplina criar(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public List<Disciplina> listar() {
		return disciplinaRepository.findAll();
	}

	public Disciplina buscar(Long id) {
		return disciplinaRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada com o id " + id));
	}

	public void excluir(Long id) {
		if (!disciplinaRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada com o id " + id);
		}
		disciplinaRepository.deleteById(id);
	}

	public Disciplina atualizar(Disciplina disciplina, Long id) {
		Disciplina existente = buscar(id);

		existente.setNome(disciplina.getNome());
		existente.setCargaHoraria(disciplina.getCargaHoraria());

		return disciplinaRepository.save(existente);
	}
}
