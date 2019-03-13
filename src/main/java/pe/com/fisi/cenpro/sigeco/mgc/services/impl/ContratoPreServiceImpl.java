package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import static pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil.CONTRATO_ACTIVO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil.CONTRATO_DESHABILITADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil.CONTRATO_ULTIMO_GENERADO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fisi.cenpro.sigeco.mgc.domain.ContratoPre;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HistoriaClinica;
import pe.com.fisi.cenpro.sigeco.mgc.repository.ContratoPreRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.ContratoPreService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HistoriaClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ContratoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.ContratoTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;

@Service
public class ContratoPreServiceImpl implements ContratoPreService {

	private final ContratoPreRepository contratoPreRepository;

	@Autowired
	private HistoriaClinicaService historiaService;

	@Autowired
	public ContratoPreServiceImpl(ContratoPreRepository contratoPreRepository) {
		this.contratoPreRepository = contratoPreRepository;
	}

	@Transactional
	@Override
	public int generarContrato(int nroHC) {
		HistoriaClinicaBO hcBo = historiaService.obtnerHistoriaClinica(nroHC);
		List<ContratoBO> lista = ContratoTransformer
				.transformObjectsToListBo(contratoPreRepository.obtenerContratoActivoPorHC(nroHC));
		if (hcBo != null && lista.size() == 0) {
			ContratoPre contrato = new ContratoPre();
			HistoriaClinica hc = new HistoriaClinica();
			hc.setNroHC(hcBo.getDatosGeneralesPaciente().getNroHC());
			contrato.setHistoriaClinica(hc);
			contrato.setFecha(DateUtil.getCurrentDateWithTimeZone());
			ContratoPre anterior = contratoPreRepository.obtenerUltimoContrato();
			int nroGenerado = formarNroContrato(anterior.getNroContrato());
			if (nroGenerado == -1)
				return -1;
			anterior.setEstado(CONTRATO_ACTIVO);
			contrato.setEstado(CONTRATO_ULTIMO_GENERADO);
			contrato.setNroContrato(nroGenerado);
			contratoPreRepository.saveAndFlush(anterior);
			contratoPreRepository.saveAndFlush(contrato);
			return contrato.getNroContrato();
		}
		return -1;
	}

	@Transactional
	@Override
	public boolean eliminarContrato(int nroHC) {
		List<ContratoBO> lista = ContratoTransformer
				.transformObjectsToListBo(contratoPreRepository.obtenerContratoActivoPorHC(nroHC));
		ContratoPre contrato = contratoPreRepository.obtenerUltimoContrato();
		if (lista.size() > 0) {
			if (lista.get(0).getNroContrato() == contrato.getNroContrato()) {
				contratoPreRepository.delete(contrato);
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean deshabilitarContrato(int nroHC) {
		List<ContratoBO> lista = ContratoTransformer
				.transformObjectsToListBo(contratoPreRepository.obtenerContratoActivoPorHC(nroHC));
		if (lista.size() > 0) {
			ContratoPre contrato = ContratoTransformer.transformBoToEntity(lista.get(0));
			contrato.setEstado(CONTRATO_DESHABILITADO);
			contratoPreRepository.saveAndFlush(contrato);
			return true;
		}
		return false;
	}

	@Override
	public ContratoBO obtenerUltimo() {
		return ContratoTransformer.transformToBo(contratoPreRepository.obtenerUltimoContrato());
	}

	@Override
	public ContratoBO obtenerUltimo(int nroHC) {
		return null;
	}

	private int formarNroContrato(Integer ultimoNroC) {
		int anioActual = DateUtil.getActualYear();
		int anio = Integer.parseInt(ultimoNroC.toString().substring(0, 4));
		int nroFormado = (anioActual == anio) ? (nroFormado = ultimoNroC + 1)
				: (anioActual > anio) ? (anioActual * 10000 + 1) : -1;
		return nroFormado;
	}
}
