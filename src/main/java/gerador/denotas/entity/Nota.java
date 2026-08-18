package gerador.denotas.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade {@code nota}.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nota", uniqueConstraints = {
	@UniqueConstraint(name = "uk_nota_aluno_disciplina_unidade", columnNames = { "aluno_id", "disciplina_id", "unidade_id" })
}, indexes = {
	@Index(name = "idx_nota_aluno", columnList = "aluno_id"),
	@Index(name = "idx_nota_disciplina", columnList = "disciplina_id"),
	@Index(name = "idx_nota_unidade", columnList = "unidade_id")
})
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "disciplina_id", nullable = false)
	private Disciplina disciplina;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "unidade_id", nullable = false)
	private Unidade unidade;

	@Column(name = "valor", nullable = false, precision = 4, scale = 2)
	private BigDecimal valor;

	@Column(name = "observacao", columnDefinition = "TEXT")
	private String observacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lancado_por")
	private Professor lancadoPor;

	@Column(name = "lancado_em", nullable = false, updatable = false)
	private LocalDateTime lancadoEm;

	@PrePersist
	void prePersist() {
		if (lancadoEm == null) {
			lancadoEm = LocalDateTime.now();
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setLancadoPor(Professor lancadoPor) {
		this.lancadoPor = lancadoPor;
	}

	public void setLancadoEm(LocalDateTime lancadoEm) {
		this.lancadoEm = lancadoEm;
	}

	public Long getId() {
		return id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public Professor getLancadoPor() {
		return lancadoPor;
	}

	public LocalDateTime getLancadoEm() {
		return lancadoEm;
	}
}
