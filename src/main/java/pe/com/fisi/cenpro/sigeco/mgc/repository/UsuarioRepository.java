package pe.com.fisi.cenpro.sigeco.mgc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByLogin(String login);

}
