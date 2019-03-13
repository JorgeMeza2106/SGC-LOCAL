package pe.com.fisi.cenpro.sigeco.mgc.configuration.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	private Object usuarioDatos;

	public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Object usuarioDatos) {
		
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.usuarioDatos = usuarioDatos;
	}
	
	public Object getTipoUsuario() {
		return usuarioDatos;
	}
	
	public void setTipoUsuario(Object usuarioDatos) {
		this.usuarioDatos = usuarioDatos;
	}
	
}
