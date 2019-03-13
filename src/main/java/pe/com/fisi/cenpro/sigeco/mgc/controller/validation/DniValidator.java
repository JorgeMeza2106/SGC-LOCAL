package pe.com.fisi.cenpro.sigeco.mgc.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<Dni, String> {

	@Override
	public void initialize(Dni constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		
		return false;
	}

}
