package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Usuario;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UsuarioBO;

public class UsuarioTransformer{

	public static UsuarioBO transformToBO(Usuario usuario) {
		UsuarioBO usuarioBO =null;
		if(usuario!=null){
			usuarioBO = new UsuarioBO();
			usuarioBO.setDni(usuario.getDniUsuario());
			usuarioBO.setLogin(usuario.getLogin());
			usuarioBO.setApellidoMaterno(usuario.getPersona().getApellidoMaterno());
			usuarioBO.setApellidoPaterno(usuario.getPersona().getApellidoPaterno());
			usuarioBO.setNombre(usuario.getPersona().getNombre());
			usuarioBO.setRol(usuario.getRol().getNombre());
			usuarioBO.setEstado(usuario.getEstado());
			usuarioBO.setClave(usuario.getClave());
		}
		return usuarioBO;
	}

}
