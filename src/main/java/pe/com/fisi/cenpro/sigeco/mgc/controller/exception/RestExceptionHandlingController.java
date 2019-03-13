package pe.com.fisi.cenpro.sigeco.mgc.controller.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.com.fisi.cenpro.sigeco.mgc.services.exceptions.ServiceException;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;
import pe.com.fisi.cenpro.sigeco.mgc.utils.StringUtil;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandlingController {

	// nombre de metodo.nombreparametro en el metodo getProperyPath
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public List<Mensaje> handleError(HttpServletRequest request, ConstraintViolationException e) {

		return e.getConstraintViolations().stream()
				.map(constraint -> new Mensaje(StringUtil.separarDespues(constraint.getPropertyPath().toString(), "."),
						constraint.getMessage()))
				.collect(Collectors.toList());
		// clase utilitario que quite lo que quita ntes del punto

	}

	@ExceptionHandler(value = NullPointerException.class)
	public List<Mensaje> handleError1(HttpServletRequest request, ConstraintViolationException e) {

		return e.getConstraintViolations().stream()
				.map(constraint -> new Mensaje(StringUtil.separarDespues(constraint.getPropertyPath().toString(), "."),
						constraint.getMessage()))
				.collect(Collectors.toList());
		// clase utilitario que quite lo que quita ntes del punto

	}

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = ServiceException.class)
	public Mensaje capturarExcepcion(HttpServletRequest request, ServiceException e) {
		return new Mensaje(e.getMessageTitle(), e.getMessage());
	}

	@ResponseStatus(value = HttpStatus.CONFLICT)
	public Mensaje capturarErrorInterno(HttpServletRequest request, ServiceException e) {
		return new Mensaje(1, e.getMessage());
	}

}
