package gerador.denotas.repository;

import gerador.denotas.entity.Nota;
import gerador.denotas.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository  extends JpaRepository<Unidade, Long> {

}
