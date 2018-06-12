package org.udistrital.ojs.custom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.models.Rol;
import org.udistrital.ojs.models.Usuario;
import org.udistrital.ojs.services.UsuarioService;

/**
 * CustomUserDetailsService: Extiende la funcionalidad de spring security para
 * realizar el acceso a la aplicacion utilizando los modelos de la aplicacion
 *
 * @author José Daniel Peña
 * @author Cristian Nariño
 * @version 1.0
 */
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Valida la autenticidad del usuario
	 * 
	 * @param username:
	 *            Nombre de usuario ingresado
	 * @return User: Objeto spring con los datos del usuario autenticado
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		// Validar la existencia del usuario
		Usuario usuario = usuarioService.validar(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("User not authorized.");
		} else {
			// Cargar el perfil del usuario
			List<GrantedAuthority> authorities = buildUserAuthority(usuario.getRol());
			return buildUserForAuthentication(usuario, authorities);
		}
	}

	/**
	 * Crea la lista con los perfiles del usuario
	 * 
	 * @param rol: Perfil del usuario            
	 * @return setAuths: List con los perfiles
	 */
	private List<GrantedAuthority> buildUserAuthority(Rol rol) {

		Set<GrantedAuthority> setAuths = new HashSet<>();

		setAuths.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));

		return new ArrayList<>(setAuths);
	}

	/**
	 * Contruye el objeto User de Spring
	 * 
	 * @param usuario: Objeto con los datos del usuario            
	 * @param authorities: List de perfiles
	 * @return User: Objeto spring de autenticación
	 */
	private User buildUserForAuthentication(Usuario usuario, List<GrantedAuthority> authorities) {
		boolean enabled = true;
		boolean accountNotExpired = true;
		boolean credentialsNotExpired = true;
		boolean accountNotLocked = true;

		// Construye el usuario que se carga en sesion
		return new User(usuario.getCorreo(), usuario.getContrasena(), enabled, accountNotExpired, credentialsNotExpired,
				accountNotLocked, authorities);
	}

}
