package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Alumno;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UsuarioBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.Diagnostico.DiagnosticoAlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;

@Service
public class AlumnoTransformer{

//	@Autowired
//	@Qualifier("cursoTransformer")
//	Transformer<Curso, CursoBO, CursoBO> transformerCurso;
//	
//	@Autowired
//	@Qualifier("alumnoServiceImplDeprecated")
//	AlumnoServiceDeprecated alumnoService;
	
//	@Override
//	public Alumno transformToE(AlumnoBO f) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public AlumnoBO transformToBO(Alumno e) {
//		// TODO Auto-generated method stub
//		AlumnoBO alumnoBO = new AlumnoBO();
//		
//		
//		Persona p = e.getPersona();
//		
//		
//		
//		//Cursos
//		List<CursoBO> listCursoBO;
//
//		
//		List<Curso> listCurso = new ArrayList<Curso>();
//
//		listCursoBO = alumnoService.obtenerCursosPorDniAlumno(e.getDniPersona());
//		System.out.println(listCursoBO);
//		//Datos de Usuario
//		UsuarioBO usuarioBO = new UsuarioBO();
//		usuarioBO.setNombre(p.getNombre());
//		usuarioBO.setApellidoMaterno(p.getApellidoMaterno());
//		usuarioBO.setApellidoPaterno(p.getApellidoPaterno());
//				
//		alumnoBO.setCursosActuales(listCursoBO);
//		alumnoBO.setCodigo(e.getCodigoAlumno());
//		
//		return alumnoBO;
//	}
	
//	@Override
//	public List<Alumno> transformToE(List<AlumnoBO> lbos) {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}
//
//	@Override
//	public List<AlumnoBO> transformToBO(List<Alumno> lens) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	public static AlumnoBO transformToBo(Alumno alumno){
		AlumnoBO alumnoBO = null;
		if(alumno!=null){
			alumnoBO = new AlumnoBO();
			alumnoBO.setCodigo(alumno.getCodigoAlumno());
			UsuarioBO uBO = new UsuarioBO();
			uBO.setNombre(alumno.getPersona().getNombre());			
			uBO.setApellidoPaterno(alumno.getPersona().getApellidoPaterno());
			uBO.setApellidoMaterno(alumno.getPersona().getApellidoMaterno());
			alumnoBO.setCursosActuales(CursoTransformer.transformToListBo(alumno.getCursos()));
			alumnoBO.setUsuarioBO(uBO);
		}
		return alumnoBO;
	}
	
	public static DiagnosticoAlumnoBO transformToBo2(Alumno alumno){
		DiagnosticoAlumnoBO alumnoBO = null;
		if(alumno!=null){
			alumnoBO = new DiagnosticoAlumnoBO();
			alumnoBO.setCodigo(alumno.getCodigoAlumno());
			alumnoBO.setNombre(alumno.getPersona().getNombre() + " "
					+ alumno.getPersona().getApellidoPaterno() + alumno.getPersona().getApellidoMaterno());
			alumnoBO.setCelular(alumno.getPersona().getDatosPersona().getTelefonoCelular());
			alumnoBO.setEmail(alumno.getPersona().getDatosPersona().getEmail());
		}
		return alumnoBO;
	}
	
	public static DiagnosticoAlumnoBO transformObjectToBo(Object[] o){
		DiagnosticoAlumnoBO bo = null;
		if (o != null) {
			bo = new DiagnosticoAlumnoBO();
			bo.setCodigo(AppUtil.ObjectToString(o[0]));
			StringBuilder nombre = new StringBuilder();
			nombre.append(o[1].toString()).append(" ");
			nombre.append(o[2].toString()).append(", ");
			nombre.append(o[3].toString()).append(" ");
			bo.setNombre(AppUtil.ObjectToString(nombre));
			bo.setCelular(AppUtil.ObjectToString(o[4]));
			bo.setEmail(AppUtil.ObjectToString(o[5]));			
		}
		return bo;
	}
	
	public static List<DiagnosticoAlumnoBO> transformObjectsToListBo(List<Object[]> objs){		
		List<DiagnosticoAlumnoBO> lista = null;
		if (objs != null) {
			lista = new ArrayList<>();
			for (Object[] obj : objs) {
				lista.add(transformObjectToBo(obj));				
			}
		}
		return lista;
	}
}
