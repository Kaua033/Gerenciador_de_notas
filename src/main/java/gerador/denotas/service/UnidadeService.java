package gerador.denotas.service;

import java.util.List;

import gerador.denotas.entity.Unidade;
import gerador.denotas.repository.UnidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UnidadeService {

	private final UnidadeRepository unidadeRepository;

	public UnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}

	public Unidade criar(Unidade unidade) {
		return unidadeRepository.save(unidade);
	}

	public List<Unidade> listar() {
		return unidadeRepository.findAll();
	}

	public Unidade buscar(Long id) {
		return unidadeRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada com o id " + id));
	}

	public void excluir(Long id) {
		if (!unidadeRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrada com o id " + id);
		}
		unidadeRepository.deleteById(id);
	}

	public Unidade atualizar(Unidade unidade, Long id) {
		Unidade existente = buscar(id);

		existente.setNome(unidade.getNome());
		existente.setOrdem(unidade.getOrdem());
		existente.setAnoLetivo(unidade.getAnoLetivo());
		existente.setDataInicio(unidade.getDataInicio());
		existente.setDataFim(unidade.getDataFim());

		return unidadeRepository.save(existente);
	}
}
