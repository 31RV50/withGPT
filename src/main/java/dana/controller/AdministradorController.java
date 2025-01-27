package dana.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dana.model.Causa;
import dana.model.Donacion;
import dana.service.CausaService;
import dana.service.IDonacionService;
import dana.service.IIngresoService;
import dana.service.ISolicitanteService;
import dana.service.IUsuarioService;



@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private CausaService causaService;
	
	@Autowired
	private ISolicitanteService solicitanteService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IDonacionService donacionService;
	
	@Autowired IIngresoService ingresoService;
	
	private Logger logg= LoggerFactory.getLogger(AdministradorController.class);

	@GetMapping("")
	public String home(Model model) {

		List<Causa> causas = causaService.findAll();
		model.addAttribute("causa", causas);
		
		List<Donacion> donaciones = donacionService.findAll();
		model.addAttribute("donacion", donaciones);


		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	@GetMapping("/solicitantes")
	public String solicitantes(Model model) {
		CausaService solicicitanteService = null;
		model.addAttribute("solicitantes", solicicitanteService.findAll());
		return "administrador/solicitantes";
	}
	@GetMapping("/donaciones")
	public String donaciones(Model model) {
		model.addAttribute("donaciones", donacionService.findAll());
		return "administrador/donaciones";
	}
	@GetMapping("/causas")
	public String causas(Model model) {
		model.addAttribute("causas", causasService.findAll());
		return "administrador/causas";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logg.info("Id de la donacion {}",id);
		Donacion donacion= donacionService.findById(id).get();
		
		model.addAttribute("detalles", donacion.getDetalle());
		
		return "administrador/detalleDonacion";
	}
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logg.info("Id de la causa {}",id);
		Causa causa= causaService.findById(id).get();
		
		model.addAttribute("detalles", causa.getDetalle());
		
		return "administrador/detalleCausa";
	}

	public ISolicitanteService getSolicitanteService() {
		return solicitanteService;
	}

	public void setSolicitanteService(ISolicitanteService solicitanteService) {
		this.solicitanteService = solicitanteService;
	}
	
	
}

