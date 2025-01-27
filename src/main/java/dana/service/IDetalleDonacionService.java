package dana.service;

import java.util.Optional;

import dana.model.Causa;
import dana.model.DetalleDonacion;
import dana.model.Donacion;

public interface IDetalleDonacionService {
	
	DetalleDonacion save(DetalleDonacion detalleDonacion);
	Optional<DetalleDonacion> findByOrdenAndCausa(Donacion donacion, Causa causa);

}

