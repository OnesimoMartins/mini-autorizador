package com.sysmap.miniautorizador.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "^[0-9]{4}",
message = "a senha do cartão deve ser constituído por 4 algarismos.")
@NotBlank
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = {})
public @interface SenhaCartaoValida {

    String message() default "a senha do cartão deve ser constituído por 4 algarismos.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
