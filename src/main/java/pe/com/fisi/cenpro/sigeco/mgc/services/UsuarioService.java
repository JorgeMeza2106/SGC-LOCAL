package pe.com.fisi.cenpro.sigeco.mgc.services;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UsuarioBO;

public interface UsuarioService {

	public UsuarioBO buscarPorNombre(String login);
}
