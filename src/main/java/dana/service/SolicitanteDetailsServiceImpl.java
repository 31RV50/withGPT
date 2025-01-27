package dana.service;


import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dana.model.Solicitante;
import dana.repository.SolicitanteRepository;

@Service
public class SolicitanteDetailsServiceImpl implements SolicitanteDetailsService {
	
	private SolicitanteRepository solicitanteRepository = null;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // Constructor con inyección de dependencias
    public void UserDetailServiceImpl(SolicitanteRepository solicitanteRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.solicitanteRepository = solicitanteRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar usuario por nombre de solicitante (o cualquier campo único)
        Solicitante solicitante = solicitanteRepository.findByUsername(username); // Asegúrate de tener este método en tu repositorio

        // Si no se encuentra el solicitante, lanzar excepción
        if (solicitante == null) {
            throw new UsernameNotFoundException("Solicitante no encontrado: " + username);
        }

        // Asumiendo que 'tipo' es el campo que indica el rol, prepends 'ROLE_' si es necesario
        String role = "ROLE_" + solicitante.getTipo().toUpperCase(); // Asumimos que 'tipo' es el rol del usuario

        // Crear un objeto UserDetails para la autenticación
        // Usamos el nombre de usuario, la contraseña (cifrada) y las autoridades (roles) del solicitante
        return User.builder()
                .username(solicitante.getUsername())  // Nombre de solicitante
                .password(solicitante.getPassword())  // Contraseña cifrada
                .roles(role)  // Usamos el campo 'tipo' como rol, con 'ROLE_' agregado
                .build();
    }

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}
}
