
package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.security.SecurityUser;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Administrativo;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HorarioClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.AlumnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HorarioClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.UsuarioService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AdministrativoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DoctorBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaAdministrativoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UsuarioBO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private HorarioClinicaService horarioClinicaService;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		UsuarioBO usuarioBO = usuarioService.buscarPorNombre(login);

		if (usuarioBO != null) {
			AlumnoBO alumnoBO = null;
			AdministrativoBO administrativoBO = null;
			String rol = usuarioBO.getRol();

			switch (rol) {
			case "ALUMNO":
				alumnoBO = alumnoService.findOne(usuarioBO.getDni());
				alumnoBO.setUsuarioBO(usuarioBO);
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						alumnoBO);
			case "ADMIN_CC":
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						usuarioBO);
			case "ADMIN_OA":
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						usuarioBO);
			case "ADMIN_AG":
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						usuarioBO);
			case "ADMIN_AD":
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						usuarioBO);
			case "ADMIN_OD":
				DoctorBO doctorBO = new DoctorBO(usuarioBO.getDni(), usuarioBO.getNombre(),
						usuarioBO.getApellidoPaterno(), usuarioBO.getApellidoMaterno());				
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						doctorBO);
			case "ADMIN_CL":
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						usuarioBO);
			case "ADMIN_CA":
				return new SecurityUser(usuarioBO.getLogin(), usuarioBO.getClave(),
						usuarioBO.getEstado().equals("ACTIVO"), true, true, true, getGrantedAuthorities(usuarioBO),
						usuarioBO);
			default:
				throw new UsernameNotFoundException(login);
			}
		} else{
			System.out.println("No hay usuario");
			throw new UsernameNotFoundException(login);
		}

	}

	private List<GrantedAuthority> getGrantedAuthorities(UsuarioBO user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));
		return authorities;
	}

}