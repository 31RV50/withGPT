package dana.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dana.model.Donacion;
import dana.model.Usuario;
import dana.service.IDonacionService;
import dana.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IDonacionService donacionService;
	
	BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
	
	
	// /usuario/registro
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword( passEncode.encode(usuario.getPassword()));
		usuarioService.save(usuario);		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<Usuario> user=usuarioService.findByEmail(usuario.getEmail());
		//logger.info("Usuario de db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/donaciones")
	public String obtenerDonaciones(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		Usuario usuario= usuarioService.findById(  Integer.parseInt(session.getAttribute("idusuario").toString()) ).get();
		List<Donacion> donaciones= donacionService.findByUsuario(usuario);
		logger.info("donaciones {}", donaciones);
		
		model.addAttribute("donaciones", donaciones);
		
		return "usuario/donaciones";
	}
	
	@GetMapping("/causa/{id}")
	public String detalleDonacion(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la donacion: {}", id);
		Optional<Donacion> donacion=donacionService.findById(id);
		
		model.addAttribute("causas", donacion.get().getCausa());
		
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/causadonacion";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion( HttpSession session ) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}
}


