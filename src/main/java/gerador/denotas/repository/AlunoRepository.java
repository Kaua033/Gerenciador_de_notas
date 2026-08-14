package gerador.denotas.repository;

import java.util.Optional;

import gerador.denotas.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByMatricula(String matricula);

}
