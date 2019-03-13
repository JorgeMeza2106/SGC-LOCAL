package pe.com.fisi.cenpro.sigeco.mgc.controller.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;
import pe.com.fisi.cenpro.sigeco.mgc.utils.StringUtil;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(HttpServletRequest request, Exception e) {

		return "errorPage";
	}
	
	@ExceptionHandler(value=NullPointerException.class)
	public List<Mensaje> handleError1(HttpServletRequest request, ConstraintViolationException e) {
		
		return e.getConstraintViolations()
			.stream()
			.map( constraint -> new Mensaje( StringUtil.separarDespues(constraint.getPropertyPath().toString(), ".") ,  
			          constraint.getMessage())).collect(Collectors.toList());
			//clase utilitario que quite lo que quita ntes del punto
		
	}

}