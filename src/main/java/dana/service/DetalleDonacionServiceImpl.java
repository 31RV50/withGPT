package dana.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dana.model.Causa;
import dana.model.DetalleDonacion;
import dana.model.Donacion;
import dana.repository.IDetalleDonacionRepository;

@Service
public class DetalleDonacionServiceImpl implements IDetalleDonacionService{
	
	@Autowired
	private IDetalleDonacionRepository detalleDonacionRepository;

	@Override
	public DetalleDonacion save(DetalleDonacion detalleDonacion) {
		
		return detalleDonacionRepository.save(detalleDonacion);
	}

	@Override
	public Optional<DetalleDonacion> findByOrdenAndCausa(Donacion donacion, Causa causa) {
		
		return Optional.empty();
	}

}
