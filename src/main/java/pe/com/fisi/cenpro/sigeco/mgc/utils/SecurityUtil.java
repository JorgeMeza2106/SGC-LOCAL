package pe.com.fisi.cenpro.sigeco.mgc.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.security.SecurityContextFacade;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UsuarioBO;

public class SecurityUtil {
	
	public static String getDniUsuario() {
		Object object = SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();
		if(object instanceof AlumnoBO) {
			return ((AlumnoBO) object).getUsuarioBO().getDni();
		} else if(object instanceof UsuarioBO) {
			return ((UsuarioBO) object).getDni();
		} else {
			return null;
		}
	}
	
    public static String getNombreUsuario(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
