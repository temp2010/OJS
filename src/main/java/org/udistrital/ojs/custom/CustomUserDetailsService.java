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

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.validar(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("User not authorized.");
		} else {
			List<GrantedAuthority> authorities = buildUserAuthority(usuario.getRol());
			return buildUserForAuthentication(usuario, authorities);
		}
		
	}

	private List<GrantedAuthority> buildUserAuthority(Rol rol) {

		Set<GrantedAuthority> setAuths = new HashSet<>();

		setAuths.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));

		return new ArrayList<>(setAuths);
	}

	private User buildUserForAuthentication(Usuario usuario, List<GrantedAuthority> authorities) {
		boolean enabled = true;
		boolean accountNotExpired = true;
		boolean credentialsNotExpired = true;
		boolean accountNotLocked = true;

		return new User(usuario.getCorreo(), usuario.getContrasena(), enabled, accountNotExpired, credentialsNotExpired,
				accountNotLocked, authorities);
	}

}
