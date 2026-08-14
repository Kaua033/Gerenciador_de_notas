package gerador.denotas.controller;

import java.time.LocalDateTime;

import gerador.denotas.repository.AlunoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller das páginas da aplicação.
 *
 * <p>Serve os templates Thymeleaf localizados em {@code src/main/resources/templates}.</p>
 */
@Controller
public class HomeController {

	private final AlunoRepository alunoRepository;

	public HomeController(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@GetMapping("/")
	public String index(Authentication authentication, Model model) {
		model.addAttribute("titulo", "Denotas");
		model.addAttribute("mensagem", "Bem-vindo ao Gerenciador de Notas!");
		model.addAttribute("dataAtual", LocalDateTime.now());

		// O username da autenticação é a matrícula do aluno logado
		alunoRepository.findByMatricula(authentication.getName())
			.ifPresent(aluno -> model.addAttribute("aluno", aluno));

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
