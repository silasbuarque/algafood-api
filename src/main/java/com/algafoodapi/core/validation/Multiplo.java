package com.algafoodapi.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MultiploValidation.class })
public @interface Multiplo {

    String message() default "Taxa frete nao é multiplo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int numero();

}
