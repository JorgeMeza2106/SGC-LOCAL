package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Usuario;
import pe.com.fisi.cenpro.sigeco.mgc.repository.UsuarioRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.UsuarioService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UsuarioBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.UsuarioTransformer;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UsuarioBO buscarPorNombre(String login) {
		Usuario usuario = usuarioRepository.findByLogin(login);
		return UsuarioTransformer.transformToBO(usuario);
	}

}
