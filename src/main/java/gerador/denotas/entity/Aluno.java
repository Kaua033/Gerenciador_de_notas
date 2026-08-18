package gerador.denotas.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade {@code aluno}.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "turma_id", nullable = false)
	private Turma turma;

	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Column(name = "matricula", nullable = false, unique = true, length = 30)
	private String matricula;

	@Column(name = "senha_hash", nullable = false, length = 255)
	private String senhaHash;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "sobrenome", nullable = false, length = 100)
	private String sobrenome;

	@Column(name = "telefone", length = 20)
	private String telefone;

	@Column(name = "email", length = 150)
	private String email;

	@Column(name = "foto_url", length = 255)
	private String fotoUrl;

	@Column(name = "nome_pai", length = 150)
	private String nomePai;

	@Column(name = "telefone_pai", length = 20)
	private String telefonePai;

	@Column(name = "nome_mae", length = 150)
	private String nomeMae;

	@Column(name = "telefone_mae", length = 20)
	private String telefoneMae;

	@Column(name = "criado_em", nullable = false, updatable = false)
	private LocalDateTime criadoEm;

	@PrePersist
	void prePersist() {
		if (criadoEm == null) {
			criadoEm = LocalDateTime.now();
		}
	}

	public static Long setid(Long id) { return id;	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public void setTelefonePai(String telefonePai) {
		this.telefonePai = telefonePai;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public void setTelefoneMae(String telefoneMae) {
		this.telefoneMae = telefoneMae;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Turma getTurma() {
		return turma;
	}

	public String getNome() {
		return nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public String getNomePai() {
		return nomePai;
	}

	public String getTelefonePai() {
		return telefonePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public String getTelefoneMae() {
		return telefoneMae;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
}
