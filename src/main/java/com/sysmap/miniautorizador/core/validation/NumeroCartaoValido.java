package com.sysmap.miniautorizador.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = "^[0-9]{16}",
message = "O número de cartão deve ser constituído por 16 algarismos.")
@NotBlank
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = {})
public @interface NumeroCartaoValido {

    String message() default "O número de cartão deve ser constituído por 16 algarismos.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
