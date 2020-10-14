package com.zup.bootcamp.validations;

import com.zup.bootcamp.services.ClienteService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    private final ClienteService clienteService;

    @Override
    public void initialize(EmailUnico constraintAnnotation) {
        // Do nothing because of X and Y.
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !clienteService.isExisteEmail(email);
    }
}
