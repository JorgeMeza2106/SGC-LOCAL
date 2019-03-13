package pe.com.fisi.cenpro.sigeco.mgc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.com.fisi.cenpro.sigeco.mgc.utils.SecurityUtil;

@Controller
public class UsuarioController {

	@RequestMapping(value={"/", "home"})
	public String index(Model model) {
		return "login";
	}
	
    @GetMapping(value = "/admin_cc")
    public String adminCcPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario()); 
        return "redirect:/admin_cc/citas/listar";
    }
    
    @GetMapping(value = "/admin_oa")
    public String adminOaPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario()); 
        return "redirect:/admin_oa/citas/listar";
    }
    
    @GetMapping(value = "/admin_ad")
    public String adminOdPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario()); 
        return "redirect:/admin_ad/historias/listar/";
    }
    
    @GetMapping(value = "/admin_ag")
    public String adminAdPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario()); 
        return "redirect:/admin_ag/citas/listar";
    }
 
    @GetMapping(value = "/admin_al")
    public String adminAlPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario());
        return "admin";
    }
    
    @GetMapping(value = "/admin_od")
    public String adminOdAlPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario());
        return "redirect:/admin_od/historias/listar/";
    }
    
    @GetMapping(value = "/admin_cl")
    public String adminClAlPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario());
		return "redirect:/admin_cl/pacientes/listar";
    }
 
    @GetMapping(value = "/alumno")
    public String alumnoPage(ModelMap model) {

        model.addAttribute("user", SecurityUtil.getNombreUsuario());
        return "redirect:/alumno/asignaciones/listar";
    }
    
    @GetMapping(value = "/admin_ca")
    public String adminCaAlPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario());
        return "redirect:/admin_ca/historias/listar/";
    }
 
    @GetMapping(value = "/Acceso_Denegado")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", SecurityUtil.getNombreUsuario());
        return "accessDenied";
    }
 
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
  

 

}
