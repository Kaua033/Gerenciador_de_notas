package gerador.denotas.service;

import gerador.denotas.entity.Aluno;
import gerador.denotas.entity.Nota;
import gerador.denotas.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private NotaRepository notaRepository;


    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public Nota criar(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota buscar(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada com o id " + id));
    }

    public List<Nota> listar(){
        return notaRepository.findAll();
    }

    public void excluir(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new IllegalArgumentException("Nota não encontrada com o id " + id);
        }
        notaRepository.deleteById(id);
    }

    public Nota atualizar( Nota nota, Long id){
        Nota existente = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada com o id " + id));

        existente.setAluno(nota.getAluno());
        existente.setDisciplina(nota.getDisciplina());
        existente.setId(nota.getId());
        existente.setUnidade(nota.getUnidade());
        existente.setLancadoEm(nota.getLancadoEm());
        existente.setLancadoPor(nota.getLancadoPor());
        existente.setValor(nota.getValor());

        return notaRepository.save(existente);
    }

}
