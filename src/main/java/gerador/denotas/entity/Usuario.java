package gerador.denotas.entity;

import java.time.LocalDateTime;

import gerador.denotas.enums.RoleUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade {@code usuario}.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Column(name = "email", nullable = false, unique = true, length = 150)
	private String email;

	@Column(name = "senha_hash", nullable = false, length = 255)
	private String senhaHash;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 20)
	private RoleUsuario role = RoleUsuario.ALUNO;

	@Column(name = "criado_em", nullable = false, updatable = false)
	private LocalDateTime criadoEm;

	@PrePersist
	void prePersist() {
		if (criadoEm == null) {
			criadoEm = LocalDateTime.now();
		}
	}
}
