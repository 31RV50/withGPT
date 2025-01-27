package dana.controller;

import java.io.IOException;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import dana.model.Usuario;
import dana.service.IUsuarioService;
import dana.service.Solicitud;
import dana.service.UploadFileService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudController {
private final Logger LOGGER = LoggerFactory.getLogger(SolicitudController.class);
	
	@Autowired
	private SolicitudService causaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("solicitudes", solicitudService.findAll());
		return "solicitudes/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "solicitudes/create";
	}
	
	@PostMapping("/save")
	public String save(Solicitud solicitud, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("Este es el objeto solicitud {}",solicitud);
		
		
		Usuario u= usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString() )).get();
		solicitud.setUsuario(u);	
		
		//imagen
		if (solicitud.getId()==null) { // cuando se crea una solicitud
			String nombreImagen= upload.saveImage(file);
			solicitud.setImagen(nombreImagen);
		}else {
			
		}

		solicitudService.save(solicitud);
		return "redirect:/solicitudes";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Solicitud solicitud= new Solicitud();
		Optional<Solicitud> optionalSolicitud=solicitudService.get(id);
		solicitud= optionalSolicitud.get();
		
		LOGGER.info("Este es el objeto solicitud {}",solicitud);
		model.addAttribute("solicitud", solicitud);
		
		return "solicitudes/edit";
	}
	
	@PostMapping("/update")
	public String update(Solicitud solicitud, @RequestParam("img") MultipartFile file ) throws IOException {
		Solicitud p= new Solicitud();
		p=solicitudService.get(solicitud.getId()).get();
		
		if (file.isEmpty()) { // editamos la solicitud pero no cambiamos la imagem
			
			solicitud.setImagen(p.getImagen());
		}else {// cuando se edita tbn la imagen			
			//eliminar cuando no sea la imagen por defecto
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen= upload.saveImage(file);
			solicitud.setImagen(nombreImagen);
		}
		solicitud.setUsuario(p.getUsuario());
		solicitudService.update(solicitud);		
		return "redirect:/solicitudes";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Solicitud p = new Solicitud();
		p=solicitudService.get(id).get();
		
		//eliminar cuando no sea la imagen por defecto
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		
		solicitudService.delete(id);
		return "redirect:/solicitudes";
	}
	
	
}

