package com.algafoodapi.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidation implements ConstraintValidator<Multiplo, Number> {

    private int multiploValue;

    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.multiploValue = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (number != null) {
            var valorDecimal = BigDecimal.valueOf(number.doubleValue());
            var valorMultiplo = BigDecimal.valueOf(multiploValue);
            var resto = valorDecimal.remainder(valorMultiplo);

            isValid = BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return isValid;
    }
}
