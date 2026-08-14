package gerador.denotas.repository;

import gerador.denotas.entity.Disciplina;
import gerador.denotas.entity.TurmaDisciplinaProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaDisciplinaProfessorRepository extends JpaRepository<TurmaDisciplinaProfessor, Long> {

}
