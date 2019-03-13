package pe.com.fisi.cenpro.sigeco.mgc.controller.mantenimiento;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.fisi.cenpro.sigeco.mgc.dto.form.ClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.dto.result.ClinicaResult;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.ConstantesGenerales;


@RequestMapping("/clinica")
public @RestController class ClinicaController
{
    private @Autowired ClinicaService clinicaService;

    @GetMapping(params = "accion=buscarTodos")
    public List<ClinicaResult> buscarTodos()
    {
        return clinicaService.buscarTodos();
    }

    @PostMapping
    public String registrarClinica(@RequestBody ClinicaForm clinicaForm, Errors error)
    {
        clinicaService.registrarClinica(clinicaForm);
        return ConstantesGenerales.REGISTRO_EXITOSO;
    }

    @PutMapping
    public String actualizarClinica(@RequestBody ClinicaForm clinicaForm, Errors error)
    {
        clinicaService.actualizarClinica(clinicaForm);
        return ConstantesGenerales.ACTUALIZACION_EXITOSA;
    }

    @DeleteMapping
    public String eliminarClinica(@RequestBody ClinicaForm clinicaForm, Errors error)
    {
        clinicaService.eliminarClinica(clinicaForm);
        return ConstantesGenerales.ELIMINACION_EXITOSA;
    }
}