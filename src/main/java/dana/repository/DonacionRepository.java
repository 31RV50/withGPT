package dana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dana.model.Donacion;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Integer>{

}
