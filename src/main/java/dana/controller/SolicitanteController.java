package dana.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dana.model.Causa;
import dana.model.Donacion;
import dana.model.Solicitante;
import dana.service.ICausaService;
import dana.service.ISolicitanteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/solicitante")
public class SolicitanteController {
private final Logger logger= LoggerFactory.getLogger(SolicitanteController.class);
	
	@Autowired
	private ISolicitanteService solicitanteService;
	
	@Autowired
	private ICausaService causaService;
	
	BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
	
	
	// /Solicitante/registro
	@GetMapping("/registro")
	public String create() {
		return "solicitante/registro";
	}
	
	@PostMapping("/save")
	public String save(Solicitante solicitante) {
		logger.info("Solicitante registro: {}");
		solicitante.setTipo("USER_SOLICITANTE");
	    solicitante.setPassword( passEncode.encode(solicitante.getPassword()));
		solicitanteService.save(solicitante);		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "solicitante/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Solicitante solicitante, HttpSession session) {
		logger.info("Accesos : {}", solicitante);
		
		Optional<Solicitante> user=solicitanteService.findByEmail(solicitante.getEmail());
		logger.info("Solicitante de db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idsolicitante", user.get().getId());
			
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Solicitante no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/causas")
	public String obtenerCausas(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idsolicitante"));
		
		Solicitante solicitante= solicitanteService.findById(  Integer.parseInt(session.getAttribute("idsolicitante").toString()) ).get();
		List<Causa> causas= causaService.findBySolicitante(solicitante);
		logger.info("causas {}", causas);
		
		model.addAttribute("causas", causas);
		
		return "solicitante/causas";
	}
	
	@GetMapping("/causa/{id}")
	public String detalleCausa(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la causa: {}", id);
		Optional<Donacion> donacion=donacionService.findById(id);
		
		model.addAttribute("causas", donacion.get().getCausa());
		
		
		//session
		model.addAttribute("sesion", session.getAttribute("idsolicitante"));
		return "solicitante/causadonacion";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion( HttpSession session ) {
		session.removeAttribute("idsolicitante");
		return "redirect:/";
	}
}


