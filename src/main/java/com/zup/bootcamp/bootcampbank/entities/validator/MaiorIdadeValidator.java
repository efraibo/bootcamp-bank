package com.zup.bootcamp.bootcampbank.entities.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Slf4j
public class MaiorIdadeValidator implements ConstraintValidator<MaiorIdade, LocalDate> {

    public static final int MAIOR_IDADE = 18;

    @Override
    public void initialize(final MaiorIdade annotation) {

    }

    //ajustar para pegar mes e dia
    @Override
    public boolean isValid(final LocalDate date, final ConstraintValidatorContext context) {
        int maiorIdade = LocalDate.now().getYear() - MAIOR_IDADE;
        return maiorIdade > date.getYear();
    }
}
