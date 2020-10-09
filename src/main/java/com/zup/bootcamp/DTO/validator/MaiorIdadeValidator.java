package com.zup.bootcamp.DTO.validator;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MaiorIdadeValidator implements ConstraintValidator<MaiorIdade, LocalDate> {

    private static final int MAIOR_IDADE = 18;

    @Override
    public void initialize(final MaiorIdade annotation) {
    }

    @Override
    public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {
        final LocalDate minMaiorIdade = LocalDate.now().minusYears(MAIOR_IDADE);
        return minMaiorIdade.compareTo(date) >= NumberUtils.INTEGER_ZERO;
    }
}
