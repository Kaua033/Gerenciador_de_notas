package gerador.denotas.service;

import java.util.List;

import gerador.denotas.entity.Professor;
import gerador.denotas.repository.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	public Professor criar(Professor professor) {
		return professorRepository.save(professor);
	}

	public List<Professor> listar() {
		return professorRepository.findAll();
	}

	public Professor buscar(Long id) {
		return professorRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado com o id " + id));
	}

	public void excluir(Long id) {
		if (!professorRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado com o id " + id);
		}
		professorRepository.deleteById(id);
	}

	public Professor atualizar(Professor professor, Long id) {
		Professor existente = buscar(id);

		existente.setNome(professor.getNome());
		existente.setRegistro(professor.getRegistro());

		return professorRepository.save(existente);
	}
}
