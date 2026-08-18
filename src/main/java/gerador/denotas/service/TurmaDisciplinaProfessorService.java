package gerador.denotas.service;

import java.util.List;

import gerador.denotas.entity.TurmaDisciplinaProfessor;
import gerador.denotas.repository.TurmaDisciplinaProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TurmaDisciplinaProfessorService {

	private final TurmaDisciplinaProfessorRepository turmaDisciplinaProfessorRepository;

	public TurmaDisciplinaProfessorService(TurmaDisciplinaProfessorRepository turmaDisciplinaProfessorRepository) {
		this.turmaDisciplinaProfessorRepository = turmaDisciplinaProfessorRepository;
	}

	public TurmaDisciplinaProfessor criar(TurmaDisciplinaProfessor turmaDisciplinaProfessor) {
		return turmaDisciplinaProfessorRepository.save(turmaDisciplinaProfessor);
	}

	public List<TurmaDisciplinaProfessor> listar() {
		return turmaDisciplinaProfessorRepository.findAll();
	}

	public TurmaDisciplinaProfessor buscar(Long id) {
		return turmaDisciplinaProfessorRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vínculo turma/disciplina/professor não encontrado com o id " + id));
	}

	public void excluir(Long id) {
		if (!turmaDisciplinaProfessorRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vínculo turma/disciplina/professor não encontrado com o id " + id);
		}
		turmaDisciplinaProfessorRepository.deleteById(id);
	}

	public TurmaDisciplinaProfessor atualizar(TurmaDisciplinaProfessor turmaDisciplinaProfessor, Long id) {
		TurmaDisciplinaProfessor existente = buscar(id);

		existente.setTurma(turmaDisciplinaProfessor.getTurma());
		existente.setDisciplina(turmaDisciplinaProfessor.getDisciplina());
		existente.setProfessor(turmaDisciplinaProfessor.getProfessor());
		existente.setAnoLetivo(turmaDisciplinaProfessor.getAnoLetivo());

		return turmaDisciplinaProfessorRepository.save(existente);
	}
}
