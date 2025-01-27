package dana.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dana.model.Causa;
import dana.model.DetalleCausa;
import dana.model.DetalleDonacion;
import dana.model.Donacion;
import dana.model.Solicitante;
import dana.model.Usuario;
import dana.service.CausaService;
import dana.service.IDetalleDonacionService;
import dana.service.ISolicitanteService;
import dana.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CausaService causaService;
	
	@Autowired
	private DonacionService donacionService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ISolicitanteService solicitanteService;
	
	
	@Autowired
	private IDonacionService donacionService;
	
	@Autowired
	private ICausaService causaService;
	
	@Autowired
	private IDetalleDonacionService detalleDonacionService;
	
	@Autowired
	private IDetalleCausa detalleCausa;

	// para almacenar los detalles de la donación
	List<DetalleDonacion> detalles = new ArrayList<DetalleDonacion>();
	
	// para almacenar los detalles de la causa
		List<DetalleCausa> detalles = new ArrayList<DetalleCausa>();

	// datos de la donación
	Donacion donacion = new Donacion();

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("Causas", causaService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";
	}
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del solicitante: {}", session.getAttribute("idsolicitante"));
		
		model.addAttribute("Donaciones", donacionService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idsolicitante"));

		return "solicitante/home";
		
	}

	@GetMapping("causahome/{id}")
	public String causaHome(@PathVariable Integer id, Model model) {
		log.info("Id causa enviado como parámetro {}", id);
		Causa causa = new Causa();
		Optional<Causa> causaOptional = causaService.get(id);
		causa = causaOptional.get();

		model.addAttribute("causa", causa);

		return "usuario/causahome";
	}
	
	@GetMapping("donacionhome/{id}")
	public String causaHome(@PathVariable Integer id, Model model) {
		log.info("Id donación enviado como parámetro {}", id);
		Donacion donacion = new Donacion();
		Optional<Donacion> donacionOptional = donacionService.get(id);
		donacion = donacionOptional.get();

		model.addAttribute("donacion", donacion);

		return "solicitante/donacionhome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleDonacion detalleDonacion = new DetalleDonacion();
		Causa causa = new Causa();
		double sumaTotal = 0;

		Optional<Causa> optionalCausa = causaService.get(id);
		log.info("Causa añadida: {}", optionalCausa.get());
		log.info("Cantidad: {}", cantidad);
		causa = optionalCausa.get();

		detalleDonacion.setCantidad(cantidad);
		detalleDonacion.setImporte(causa.getImporte());
		detalleDonacion.setNombre(causa.getNombre());
		detalleDonacion.setTotal(causa.getImporte() * cantidad);
		detalleDonacion.setCausa(causa);
		
		//validar que la causa no se añada 2 veces
		Integer idCausa=causa.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getCausa().getId()==idCausa);
		
		if (!ingresado) {
			detalles.add(detalleDonacion);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		donacion.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("donacion", donacion);

		return "usuario/carrito";
	}

	// quitar una causa del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteCausaCart(@PathVariable Integer id, Model model) {

		// lista nueva de causas
		List<DetalleDonacion> donacionNueva = new ArrayList<DetalleDonacion>();

		for (DetalleDonacion : detalles) {
			if (detalleDonacion.getCausa().getId() != id) {
				donacionNueva.add(detalleDonacion);
			}
		}

		// poner la nueva lista con las causas restantes
		detalles = donacionNueva;

		double sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        donacion.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("donacion", donacion);
        
        return "usuario/carrito";
    }
    
    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        model.addAttribute("cart", detalles);
        model.addAttribute("donacion", donacion);
        
        // Session
        model.addAttribute("session", session.getAttribute("idusuario"));
        return "/usuario/carrito";
    }

    @GetMapping("/donacion")
    public String donacion(Model model, HttpSession session) {
    	String idUsuarioSession = null;
    	
    	// Obtenemos el id del usuario desde la sesión
    	
    	if ( Objects.nonNull( session.getAttribute("idusuario"))) {
    		idUsuarioSession = String.valueOf(session.getAttribute("idusuario"));
    	} else {
    		
            // Verificamos si el atributo "idusuario" está en la sesión
    		
    		   log.error("El usuario no está autenticado. Redirigiendo al inicio.");
               return "usuario/noUser";  // Redirige al home si no hay usuario en sesión	
    	}
        
        // Obtener el usuario desde el servicio
        Usuario usuario = usuarioService.findById(Integer.parseInt(idUsuarioSession)).get();
        
       } 
        @GetMapping("/causa")
        public String donacion(Model model, HttpSession session) {
        	String idSolicitanteSession = null;
        	
        	// Obtenemos el id del solicitante desde la sesión
        	
        	if ( Objects.nonNull( session.getAttribute("idsolicitante"))) {
        		idSolicitanteSession = String.valueOf(session.getAttribute("idsolicitante"));
        	} else {
        		
                // Verificamos si el atributo "idsolicitante" está en la sesión
        		
        		   log.error("El solicitante no está autenticado. Redirigiendo al inicio.");
                   return "solicitante/noUser";  // Redirige al home si no hay solicitante en sesión	
        	}
        
        	// Obtener el solicitante desde el servicio
            Solicitante solicitante = solicitanteService.findById(Integer.parseInt(idSolicitanteSession)).get();
            
            
        // Agregar los detalles de la donación y el usuario al modelo
        model.addAttribute("cart", detalles);
        model.addAttribute("donacion", donacion);    
        model.addAttribute("usuario", usuario);
        
        // Devolver la vista de resumen de la donación
        return "usuario/resumendonacion";
    }
    
    // Guardar la donación
    @GetMapping(\"/saveDonacion\")
    public String saveDonacion(HttpSession session) {
        Date fechaCreacion = new Date();
        donacion.setFechaCreacion(fechaCreacion);
        donacion.setNumero(donacionService.generarNumeroDonacion());
        
        // Usuario
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        
        donacion.setUsuario(usuario);
        donacionService.save(donacion);
        
        // Guardar detalles
        for (DetalleDonacion dt : detalles) {
            dt.setDonacion(donacion);
            detalleDonacionService.save(dt);
        }
        
        // Limpiar lista y donación
        donacion = new Donacion();
        detalles.clear();
        
        return "redirect:/";
    }
    
    @PostMapping("/search")
    public String searchCausa(@RequestParam String nombre, Model model) {
        log.info("Nombre de la causa: {}", nombre);
        List<Causa> causas = causaService.findAll().stream()
            .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(Collectors.toList());
        model.addAttribute("causas", causas);        
        return "usuario/home";
    }
}
