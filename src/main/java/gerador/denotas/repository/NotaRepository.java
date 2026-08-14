package gerador.denotas.repository;

import gerador.denotas.entity.Disciplina;
import gerador.denotas.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {

}
