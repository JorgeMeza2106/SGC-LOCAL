package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Curso;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CursoBO;

@Service
public class CursoTransformer {

	public static CursoBO transformToBo(Curso curso) {
		CursoBO cursoBO = null;
		if (curso != null) {
			cursoBO = new CursoBO();
			cursoBO.setIdCurso(curso.getIdCurso());
			cursoBO.setNombre(curso.getNombre());
		}
		return cursoBO;
	}

	public static List<CursoBO> transformToListBo(Set<Curso> listCurso) {
		List<CursoBO> listCursoBo=null;
		if(listCurso !=null){
			listCursoBo = new ArrayList<CursoBO>();
			for(Curso curso : listCurso){
				listCursoBo.add(transformToBo(curso));
			}
		}
		return listCursoBo;
	}
	
	public static List<CursoBO> transformToListBo(List<Curso> listCurso) {
		List<CursoBO> listCursoBo=null;
		if(listCurso !=null){
			listCursoBo = new ArrayList<CursoBO>();
			for(Curso curso : listCurso){
				listCursoBo.add(transformToBo(curso));
			}
		}
		return listCursoBo;
	}

}
