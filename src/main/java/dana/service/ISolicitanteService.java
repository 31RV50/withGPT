package dana.service;

import java.util.List;
import java.util.Optional;

import dana.model.Solicitante;



public interface ISolicitanteService {

	List<Solicitante> findAll();
	Optional <Solicitante> findById(Integer id);
	Solicitante save (Solicitante solicitante);
    Optional<Solicitante> findByEmail(String email);
}

