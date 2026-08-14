package gerador.denotas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança do Gerenciador de Notas.
 *
 * <p>Autenticação por <strong>form login</strong> usando a matrícula como nome de
 * usuário e a senha do aluno (armazenada como BCrypt em {@code aluno.senha_hash}).
 * O console H2 permanece liberado apenas em desenvolvimento.</p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/error").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated())
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll())
			.logout(logout -> logout
				.logoutSuccessUrl("/login?logout")
				.permitAll())
			.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/api/**"))
			.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
