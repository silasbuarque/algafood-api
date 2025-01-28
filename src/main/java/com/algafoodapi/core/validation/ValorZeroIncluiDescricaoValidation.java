package com.algafoodapi.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidation implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.valorField = constraintAnnotation.valorField();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object instanceClazz, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;

        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(instanceClazz.getClass(), valorField)
                    .getReadMethod().invoke(instanceClazz);

            String descricao = (String) BeanUtils.getPropertyDescriptor(instanceClazz.getClass(), descricaoField)
                    .getReadMethod().invoke(instanceClazz);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
            }

        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }

        return valido;
    }
}
