package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.ContratoPre;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HistoriaClinica;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ContratoBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;

@Service
public class ContratoTransformer {
	
	public static ContratoBO transformToBo (ContratoPre contrato) {
		ContratoBO contratoBO = null;
		if (contrato != null) {
			contratoBO = new ContratoBO();
			contratoBO.setNroContrato(contrato.getNroContrato());
			contratoBO.setNroHC(contrato.getHistoriaClinica().getNroHC());
			contratoBO.setFecha(contrato.getFecha());
			contratoBO.setEstado(contrato.getEstado());
		}
		return contratoBO; 
	}
	
	public static ContratoPre transformBoToEntity(ContratoBO bo){
		ContratoPre contrato = null;
		if(bo != null){
			HistoriaClinica hc = new HistoriaClinica();
			hc.setNroHC(bo.getNroHC());
			contrato = new ContratoPre();
			contrato.setNroContrato(bo.getNroContrato());
			contrato.setHistoriaClinica(hc);
			contrato.setFecha(bo.getFecha());
			contrato.setEstado(bo.getEstado());
		}
		return contrato;
	}
	
	public static List<ContratoBO> transformToListBo(Set<ContratoPre> listContrato) {
		List<ContratoBO> listContratoBo=null;
		if(listContrato !=null){
			listContratoBo = new ArrayList<ContratoBO>();
			for(ContratoPre contrato : listContrato){
				listContratoBo.add(transformToBo(contrato));
			}
		}
		return listContratoBo;
	}
	
	public static List<ContratoBO> transformToListBo(List<ContratoPre> listContrato) {
		List<ContratoBO> listContratoBo=null;
		if(listContrato !=null){
			listContratoBo= new ArrayList<ContratoBO>();
			for(ContratoPre contrato : listContrato){
				listContratoBo.add(transformToBo(contrato));
			}
		}
		return listContratoBo;
	}
	
	public static ContratoBO transformObjectToBo(Object[] o){
		ContratoBO bo = null;
		if (o != null) {
			bo = new ContratoBO();
			bo.setNroContrato(AppUtil.ObjectToInteger(o[0]));			
			bo.setNroHC(AppUtil.ObjectToInteger(o[1]));
			bo.setFecha(AppUtil.ObjectToDate(o[2]));
			bo.setEstado(AppUtil.ObjectToString(o[3]));
		}
		return bo;
	}
	
	public static List<ContratoBO> transformObjectsToListBo(List<Object[]> objs){		
		List<ContratoBO> lista = null;
		if (objs != null) {
			lista = new ArrayList<>();
			for (Object[] obj : objs) {
				lista.add(transformObjectToBo(obj));				
			}
		}
		return lista;
	}
}
