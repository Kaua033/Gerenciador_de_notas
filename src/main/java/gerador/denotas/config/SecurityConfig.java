package gerador.denotas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança do Gerenciador de Notas.
 *
 * <p>Em modo de desenvolvimento, a segurança está totalmente liberada: todas as
 * requisições são permitidas ({@code permitAll}) e o CSRF está desabilitado,
 * para não atrapalhar o uso do console H2 nem o teste da API.</p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

		return http.build();
	}

}
