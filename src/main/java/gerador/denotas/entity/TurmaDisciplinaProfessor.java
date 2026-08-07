package gerador.denotas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade {@code turma_disciplina_professor} (associação entre turma,
 * disciplina e professor para um ano letivo).
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "turma_disciplina_professor", uniqueConstraints = {
	@UniqueConstraint(name = "uk_turma_disciplina_ano", columnNames = { "turma_id", "disciplina_id", "ano_letivo" })
})
public class TurmaDisciplinaProfessor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "turma_id", nullable = false)
	private Turma turma;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "disciplina_id", nullable = false)
	private Disciplina disciplina;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;

	@Column(name = "ano_letivo", nullable = false)
	private Integer anoLetivo;
}
