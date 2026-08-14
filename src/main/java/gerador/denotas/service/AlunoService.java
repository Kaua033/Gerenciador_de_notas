package gerador.denotas.service;

import gerador.denotas.entity.Aluno;
import gerador.denotas.repository.AlunoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AlunoService {

private  Aluno aluno;
private AlunoRepository alunoRepository;

    public AlunoService(Aluno aluno, AlunoRepository alunoRepository) {
        this.aluno = aluno;
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listar(){
        return alunoRepository.findAll();
}

public void deleçao(Long id){
        alunoRepository.deleteAllById(Collections.singleton(id));
}

public Aluno criar( Aluno aluno){
 return alunoRepository.save(aluno);
}

public Aluno atualizar( Aluno aluno, Long id){
        Aluno existente = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o id " + id));

        existente.setNome(aluno.getNome());
        existente.setSobrenome(aluno.getSobrenome());
        existente.setMatricula(aluno.getMatricula());
        existente.setDataNascimento(aluno.getDataNascimento());
        existente.setTelefone(aluno.getTelefone());
        existente.setEmail(aluno.getEmail());
        existente.setFotoUrl(aluno.getFotoUrl());
        existente.setNomePai(aluno.getNomePai());
        existente.setTelefonePai(aluno.getTelefonePai());
        existente.setNomeMae(aluno.getNomeMae());
        existente.setTelefoneMae(aluno.getTelefoneMae());
        existente.setSenhaHash(aluno.getSenhaHash());
        existente.setTurma(aluno.getTurma());

        return alunoRepository.save(existente);
}

}
