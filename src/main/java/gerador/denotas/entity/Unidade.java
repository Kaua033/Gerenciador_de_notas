package gerador.denotas.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade {@code unidade}.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "unidade", uniqueConstraints = {
	@UniqueConstraint(name = "uk_unidade_ano_ordem", columnNames = { "ano_letivo", "ordem" })
})
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "ordem", nullable = false)
	private Short ordem;

	@Column(name = "ano_letivo", nullable = false)
	private Integer anoLetivo;

	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@Column(name = "data_fim")
	private LocalDate dataFim;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Short getOrdem() {
		return ordem;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setOrdem(Short ordem) {
		this.ordem = ordem;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
}
