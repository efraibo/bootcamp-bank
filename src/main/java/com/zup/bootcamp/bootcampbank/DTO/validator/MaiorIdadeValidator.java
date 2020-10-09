package com.zup.bootcamp.bootcampbank.DTO.validator;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MaiorIdadeValidator implements ConstraintValidator<MaiorIdadeConstraint, LocalDate> {

    private static final int MAIOR_IDADE = 18;

    @Override
    public void initialize(final MaiorIdadeConstraint annotation) {
    }

    @Override
    public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {
        final LocalDate minMaiorIdade = LocalDate.now().minusYears(MAIOR_IDADE);
        return minMaiorIdade.compareTo(date) >= NumberUtils.INTEGER_ZERO;
    }
}
