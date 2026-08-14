package gerador.denotas.repository;

import gerador.denotas.entity.Disciplina;
import gerador.denotas.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository  extends JpaRepository<Professor, Long> {

}
