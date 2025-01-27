package dana.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dana.model.Usuario;
import dana.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;  // Repositorio de Usuario
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constructor con inyección de dependencias
    public UserDetailServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar usuario por nombre de usuario (o cualquier campo único)
        Usuario usuario = usuarioRepository.findByUsername(username); // Asegúrate de tener este método en tu repositorio

        // Si no se encuentra el usuario, lanzar excepción
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Asumiendo que 'tipo' es el campo que indica el rol, prepends 'ROLE_' si es necesario
        String role = "ROLE_" + usuario.getTipo().toUpperCase(); // Asumimos que 'tipo' es el rol del usuario

        // Crear un objeto UserDetails para la autenticación
        // Usamos el nombre de usuario, la contraseña (cifrada) y las autoridades (roles) del usuario
        return User.builder()
                .username(usuario.getUsername())  // Nombre de usuario
                .password(usuario.getPassword())  // Contraseña cifrada
                .roles(role)  // Usamos el campo 'tipo' como rol, con 'ROLE_' agregado
                .build();
    }

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}
}
