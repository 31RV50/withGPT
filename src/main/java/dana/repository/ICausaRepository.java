package dana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dana.model.Causa;
import dana.model.Solicitante;

@Repository
public interface ICausaRepository extends JpaRepository<Causa, Integer>{
	List<Causa> findByUsuario (Solicitante solicitante);

}
