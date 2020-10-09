package com.zup.bootcamp.bootcampbank.DTO.validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CpfValidator implements ConstraintValidator<Cpf, String> {
    @Override
    public void initialize(Cpf constraintAnnotation) {

    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = Boolean.FALSE;
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
            isValid = Boolean.TRUE;
        } catch (InvalidStateException e) {
            log.error(e.getMessage());
        }

        return isValid;
    }
}
