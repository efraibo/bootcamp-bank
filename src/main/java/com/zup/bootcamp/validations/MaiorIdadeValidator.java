package com.zup.bootcamp.validations;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MaiorIdadeValidator implements ConstraintValidator<MaiorIdade, LocalDate> {

    private static final int MAIOR_IDADE = 18;

    @Override
    public void initialize(final MaiorIdade annotation) {
        // Do nothing because of X and Y.
    }

    @Override
    public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {
        final LocalDate minMaiorIdade = LocalDate.now().minusYears(MAIOR_IDADE);
        return minMaiorIdade.compareTo(date) >= NumberUtils.INTEGER_ZERO;
    }
}
