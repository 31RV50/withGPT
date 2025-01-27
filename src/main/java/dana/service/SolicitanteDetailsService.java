package dana.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SolicitanteDetailsService {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
