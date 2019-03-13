package pe.com.fisi.cenpro.sigeco.mgc.controller;

import static pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes.SPLIT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.HistoriaClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.HistoriaClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.UbigeoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.EstadoAsignacion;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UbigeoBO;

@Controller
@SessionAttributes("Diagnostico")
public class AdminDiagnosticoController {

	@Autowired
	private HistoriaClinicaService historiaClinicaService;
	@Autowired
	private UbigeoService ubigeoService;	
	
	@ModelAttribute("estadosAsignacion")
	public List<EstadoAsignacion> estadoAsignacion() {
		return Arrays.asList(EstadoAsignacion.ALL);
	}

	@ModelAttribute("estadosAsignacionNoArchivada")
	public List<EstadoAsignacion> estadoAsignacionNoArchivada() {
		return Arrays.asList(EstadoAsignacion.NO_ARCHIVADA);
	}

	@RequestMapping(value = "/admin_ad/historias/listar/", method = RequestMethod.GET)
	public String listarHistorias(Model modelo) {
		List<HistoriaClinicaGeneralBO> listHistoriaClinicaBo = historiaClinicaService.obtenerTodasHistoriasclinicas();
		modelo.addAttribute("listaHistorias", listHistoriaClinicaBo);
		modelo.addAttribute("asignacionForm", new AsignacionForm());
		return "adminDiagnosticoListarHistorias";
	}

	@RequestMapping(value = "/admin_ad/historias/registrar", method = RequestMethod.GET)
	public String registrarHClinica(Model model) {
		
		cargarDepartamentos(model);
		model.addAttribute("historiaClinicaForm", new HistoriaClinicaForm());

		return "adminDiagnosticoRegistrarHistoria";
	}	

	@RequestMapping(value = "/admin_ad/historias/registrarla", method = RequestMethod.POST)
	public String registrarHC(Model model, @Valid @ModelAttribute("historiaClinicaForm") HistoriaClinicaForm HCForm,
			BindingResult br) {
		String ubigeoNac = HCForm.getDatosPersonaBO().getUbigeoNacimiento();
		String ubigeoPro = HCForm.getDatosPersonaBO().getUbigeoProcedencia();
		
		if (br.hasErrors()) {
			List<ObjectError> erro = br.getAllErrors();
			for (ObjectError objectError : erro) {
				System.out.println(objectError.getDefaultMessage());				
			}
			cargarDepartamentos(model);
			if(!HCForm.getDatosPersonaBO().getUbigeoNacimiento().equals("-1")){
				String ubiNac[] =  ubigeoNac.split(SPLIT);
				model.addAttribute("ubigeoNac", ubigeoNac);
				model.addAttribute("provinciasNac", ubigeoService.obtenerProvinciasPorDepa(ubiNac[0]));
				model.addAttribute("distritosNac", ubigeoService.obtenerDistritosPorDepaProvincia(ubiNac[0], ubiNac[1]));
			}
			if(!HCForm.getDatosPersonaBO().getUbigeoProcedencia().equals("-1")){
				model.addAttribute("ubigeoPro", ubigeoPro);
				String ubiPro[] = ubigeoPro.split(SPLIT);
				model.addAttribute("provinciasPro", ubigeoService.obtenerProvinciasPorDepa(ubiPro[0]));
				model.addAttribute("distritosPro", ubigeoService.obtenerDistritosPorDepaProvincia(ubiPro[0], ubiPro[1]));
			}
			return "adminDiagnosticoRegistrarHistoria";
		} else {			
			List<String> lstErrores = historiaClinicaService.registrarHistoriaClinica(HCForm);
			if (lstErrores != null && lstErrores.isEmpty()) {
				System.out.println("ENTRE 2");
				return "redirect:/admin_ad/historias/listar/";
			} else {
				System.out.println("ENTRE 3");
				model.addAttribute("lstErrores", lstErrores);
				return "adminDiagnosticoRegistrarHistoria";
			}
		}
	}	
	
	@RequestMapping(value = "/admin_ad/historias/editar/{nroHC}", method = RequestMethod.GET)
	public String editarHistoria(@PathVariable Integer nroHC, Model modelo) {
		System.out.println(nroHC);
		HistoriaClinicaBO hcBO = historiaClinicaService.obtnerHistoriaClinica(nroHC);
		cargarDepartamentos(modelo);
		modelo.addAttribute("adminType", "/admin_ad");
		modelo.addAttribute("historiaClinicaDetalle", hcBO);		
		modelo.addAttribute("asignacionForm", new AsignacionForm());
		
		modelo.addAttribute("departamentos", ubigeoService.obtenerDepartamentos());
		modelo.addAttribute("error", false);		
		cargarUbigeo(modelo, hcBO);
		
		return "adminDetalleHistoria";
	}
	
	@RequestMapping(value = {"/admin_ad/historias/registrar/provincias", "/admin_ad/historias/editar/provincias"} , method = RequestMethod.GET)
	@ResponseBody
	public List<UbigeoBO> obtenerProvincias(Model model, @ModelAttribute("idDepartamento")String idDepa) {		
		//model.addAttribute("provincias", ubigeoService.obtenerProvinciasPorDepa(idDepa));
		model.addAttribute("distritos", new ArrayList<UbigeoBO>());
		
		model.addAttribute("historiaClinicaForm", new HistoriaClinicaForm());
		
		return ubigeoService.obtenerProvinciasPorDepa(idDepa);
	}
	
	@RequestMapping(value = {"/admin_ad/historias/registrar/distritos", "/admin_ad/historias/editar/distritos"}, method = RequestMethod.GET)
	@ResponseBody
	public List<UbigeoBO> obtenerDistrito(Model model,  @ModelAttribute("idDepartamento")String idDepa,  @ModelAttribute("idProvincia")String idProvincia) {		
		model.addAttribute("historiaClinicaForm", new HistoriaClinicaForm());
		return ubigeoService.obtenerDistritosPorDepaProvincia(idDepa, idProvincia);
	}
		
	
/****************************************************/
	
	private void cargarDepartamentos(Model model){
		model.addAttribute("departamentos", ubigeoService.obtenerDepartamentos());
		model.addAttribute("provincias", new ArrayList<UbigeoBO>());
		model.addAttribute("distritos", new ArrayList<UbigeoBO>());	
	}
	
	private void cargarUbigeo(Model modelo, HistoriaClinicaBO hcBO){
		cargarDepartamentos(modelo);
		if (hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento() != null) {
			if (!hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento().equals("-1") ) {
				String ubiNac[] = hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento().split(SPLIT);
				System.out.println("ubigeo nac: " + hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento());
				modelo.addAttribute("provinciasNac", ubigeoService.obtenerProvinciasPorDepa(ubiNac[0]));
				modelo.addAttribute("distritosNac", ubigeoService.obtenerDistritosPorDepaProvincia(ubiNac[0], ubiNac[1]));
			}
			
		}
		if (hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia() != null) {
			if (!hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia().equals("-1")) {
				System.out.println(hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia() );
				String ubiPro[] = hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia().split(SPLIT);
				modelo.addAttribute("provinciasPro", ubigeoService.obtenerProvinciasPorDepa(ubiPro[0]));
				modelo.addAttribute("distritosPro", ubigeoService.obtenerDistritosPorDepaProvincia(ubiPro[0], ubiPro[1]));
			}
			
		}
	}

}
