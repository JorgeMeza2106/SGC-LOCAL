package pe.com.fisi.cenpro.sigeco.mgc.controller.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NotNull
@Pattern(regexp="[0-9]{8}")
@Documented
//@Constraint(validatedBy = DniValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Dni {
    String message() default "Dni Inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
