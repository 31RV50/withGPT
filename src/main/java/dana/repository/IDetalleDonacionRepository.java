package dana.repository;

import org.springframework.stereotype.Repository;

import dana.model.DetalleDonacion;

@Repository
public interface IDetalleDonacionRepository {

	DetalleDonacion save(DetalleDonacion detalleDonacion);

}
