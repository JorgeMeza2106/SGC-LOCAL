package pe.com.fisi.cenpro.sigeco.mgc.configuration.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 
//En el momento que el usuario se loguea 
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
     
    @Override
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
  
        if (response.isCommitted()) {
        	
            System.out.println("Can't redirect");
            return;
        }
  
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
     
    protected String determineTargetUrl(Authentication authentication) {
        String url="";
         
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
         
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
 
        if (isAlumno(roles)) {
            url = "/alumno";
        } else if (isAdminCc(roles)) {
            url = "/admin_cc";
        } else if (isAdminAg(roles)) {
            url = "/admin_ag";
        } else if (isAdminOa(roles)) {
            url = "/admin_oa";
        } else if (isAdminAd(roles)) {
            url = "/admin_ad";
        } else if (isAdminOd(roles)) {
            url = "/admin_od";
        } else if (isAdminCl(roles)) {
            url = "/admin_cl";
        } else if (isAdminCa(roles)) {
            url = "/admin_ca";
        } else {
            url="/Acceso_Denegado";
        }
 
        return url;
    }
    
	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    @Override
	protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
     
    private boolean isAlumno(List<String> roles) {
        if (roles.contains("ROLE_ALUMNO")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdminAg(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_AG")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdminCc(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_CC")) {
            return true;
        }
        return false;
    }
    
    private boolean isAdminOa(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_OA")) {
            return true;
        }
        return false;
    }
    
    private boolean isAdminAd(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_AD")) {
            return true;
        }
        return false;
    }
    
    private boolean isAdminOd(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_OD")) {
            return true;
        }
        return false;
    }
    
    private boolean isAdminCl(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_CL")) {
            return true;
        }
        return false;
    }     
    
    private boolean isAdminCa(List<String> roles) {
        if (roles.contains("ROLE_ADMIN_CA")) {
            return true;
        }
        return false;
    }     
 
}