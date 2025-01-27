package dana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dana.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	// Método para encontrar al usuario por nombre de usuario
    Usuario findByUsername(String username);
}
