package dana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dana.model.Solicitante;



@Repository
public interface ISolicitanteRepository extends JpaRepository<Solicitante, Integer>{
	Optional<Solicitante> findByEmail(String email);

}
