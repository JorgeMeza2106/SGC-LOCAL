package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Ubigeo;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UbigeoBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;
public class UbigeoTransformer {
	
	public static UbigeoBO trasmformToBo(Ubigeo ubi){
		UbigeoBO ubigeoBo = null;
		if(ubi != null){
			ubigeoBo = new UbigeoBO();
			ubigeoBo.setIdUbigeo(ubi.getIdUbigeo());
			ubigeoBo.setIdDepartamento(ubi.getDepartamentoId());
			ubigeoBo.setIdProvincia(ubi.getProvinciaId());
			ubigeoBo.setIdDistrito(ubi.getDistritoId());
			ubigeoBo.setNombre(ubi.getNombre());
		}
		return ubigeoBo;
		
	}
	
	public static UbigeoBO trasmformToBo(Object[] ubi){
		UbigeoBO ubigeoBo = null;
		if(ubi != null){
			ubigeoBo = new UbigeoBO();
			ubigeoBo.setIdUbigeo(AppUtil.ObjectToInteger(ubi[0]));
			ubigeoBo.setIdDepartamento(AppUtil.ObjectToString(ubi[1]));
			ubigeoBo.setIdProvincia(AppUtil.ObjectToString(ubi[2]));
			ubigeoBo.setIdDistrito(AppUtil.ObjectToString(ubi[3]));
			ubigeoBo.setNombre(AppUtil.ObjectToString(ubi[4]));
		}
		return ubigeoBo;
		
	}
	
	public static List<UbigeoBO> transformObjectToListBo(List<Object[]> ubigeosObj){
		List<UbigeoBO> listBO = new ArrayList<>();
		for ( int i=0; i<ubigeosObj.size();i++) {			
			listBO.add(trasmformToBo(ubigeosObj.get(i)));
		}
		return listBO;
	}
	public static List<UbigeoBO> transformEntitiesToListBo(List<Ubigeo> ubigeosObj){
		List<UbigeoBO> listBO = new ArrayList<>();
		for ( int i=0; i<ubigeosObj.size();i++) {
			
			listBO.add(trasmformToBo(ubigeosObj.get(i)));
		}
		return listBO;
	}
	
}
