package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Turno;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.TurnoBO;

@Service
public class TurnoTransformer {

	public static TurnoBO transformToBo(Turno turno){
		TurnoBO turnoBO = null;
		if(turno!=null){
			turnoBO = new TurnoBO();
			turnoBO.setIdTurno(turno.getIdTurno());
			turnoBO.setEstado(turno.getEstado());
			turnoBO.setDescripcion(turno.getDescripcion());
			turnoBO.setInicio(turno.getInicio());
			turnoBO.setFin(turno.getFin());
		}
		return turnoBO;
	}
	
	public static List<TurnoBO> transformToListBo(List<Turno> lista){
		List<TurnoBO> listaBo = null;
		if(lista != null){
			listaBo = new ArrayList<>();
			for (Turno turno : lista) {
				listaBo.add(transformToBo(turno));
			}			
		}
		return listaBo;
	}
}
