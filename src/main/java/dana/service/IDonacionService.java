package dana.service;

import java.util.List;
import java.util.Optional;

import dana.model.Donacion;
import dana.model.Usuario;

public interface IDonacionService {
	
	List<Donacion> findAll();
	Optional<Donacion> findById(Integer id);
	Donacion save (Donacion donacion);
	String generarNumeroDonacion();
	List<Donacion> findByUsuario (Usuario usuario);

}
