package dana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dana.model.Solicitante;


public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer>{
	
	// Método para encontrar al usuario por nombre de solicitante
    Solicitante findByUsername(String username);

}
