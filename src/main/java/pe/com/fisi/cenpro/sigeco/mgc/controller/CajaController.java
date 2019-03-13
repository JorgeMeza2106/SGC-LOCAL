package pe.com.fisi.cenpro.sigeco.mgc.controller;

import static pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil.CONTRATO_ULTIMO_GENERADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes.M_CONTRATO_DESHABILITADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes.M_CONTRATO_ELIMINADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes.M_CONTRATO_GENERADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes.M_CONTRATO_NO_DESHABILITADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes.M_CONTRATO_NO_ELIMINADO;
import static pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes.M_CONTRATO_NO_GENERADO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javaslang.Tuple;
import javaslang.Tuple2;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.ContratoPreService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HistoriaClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ContratoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;

@Controller
public class CajaController {

	@Autowired
	private HistoriaClinicaService historiaClinicaService;
	@Autowired
	private ContratoPreService contratoService;

	@RequestMapping(value = "/admin_ca/historias/listar/", method = RequestMethod.GET)
	public String listarHistorias(Model modelo) {
		List<HistoriaClinicaGeneralBO> listHistoriaClinicaBo = historiaClinicaService.obtenerTodasHistoriasclinicas();
		modelo.addAttribute("listaHistorias", listHistoriaClinicaBo);
		modelo.addAttribute("asignacionForm", new AsignacionForm());
		ContratoBO bo = contratoService.obtenerUltimo();
		modelo.addAttribute("ultimoC", (bo.getEstado().equals(CONTRATO_ULTIMO_GENERADO) ? bo.getNroContrato() : -1));
		return "adminCajaHistorias";
	}

	@RequestMapping(value = "/admin_ca/historias/listar/generarContrato", method = RequestMethod.GET)
	public @ResponseBody Tuple2<Integer, String> generarContrato(@RequestParam("nroHC") Integer numHC, Model modelo) {		
		int numContrato = contratoService.generarContrato(numHC);	
		System.out.println("Nro de contrato generado:" + numContrato);			
		Tuple2<Integer, String> tupla = (numContrato != -1) ? Tuple.of(numContrato, M_CONTRATO_GENERADO):
			Tuple.of(numContrato, M_CONTRATO_NO_GENERADO);
		return tupla;
	}
	
	@RequestMapping(value = "/admin_ca/historias/listar/eliminarContrato", method = RequestMethod.GET)
	public @ResponseBody Tuple2<Boolean, String> eliminarContrato(@RequestParam("nroHC") Integer numHC, Model modelo) {
		Tuple2<Boolean, String> tupla  = (contratoService.eliminarContrato(numHC)) ? Tuple.of(true, M_CONTRATO_ELIMINADO)
				: Tuple.of(false, M_CONTRATO_NO_ELIMINADO);			
		return tupla;
	}
	
	@RequestMapping(value = "/admin_ca/historias/listar/deshabilitarContrato", method = RequestMethod.GET)
	public @ResponseBody Tuple2<Boolean, String> deshabilitarContrato(@RequestParam("nroHC") Integer numHC, Model modelo) {
		Tuple2<Boolean, String> tupla  = (contratoService.deshabilitarContrato(numHC)) ? Tuple.of(true, M_CONTRATO_DESHABILITADO)
				: Tuple.of(false, M_CONTRATO_NO_DESHABILITADO);			
		return tupla;
	}
}
