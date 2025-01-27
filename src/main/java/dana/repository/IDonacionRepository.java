package dana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dana.model.Donacion;
import dana.model.Usuario;

@Repository
public interface IDonacionRepository extends JpaRepository<Donacion, Integer>{

	List<Donacion> findByUsuario (Usuario usuario);
	
	

}
