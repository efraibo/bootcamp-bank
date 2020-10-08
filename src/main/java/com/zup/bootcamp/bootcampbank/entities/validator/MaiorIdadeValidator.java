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
        boolean teste = maiorIdade > date.getYear();
        log.debug("Teste agora", teste);
        return teste;
    }

//    public static boolean isValid(final LocalDate date) {
//        int maiorIdade = LocalDate.now().getYear() - MAIOR_IDADE;
//        boolean teste = maiorIdade < date.getYear();
//        log.debug("Teste agora", teste);
//        return false;
//    }
//
//    public static void main(String[] args) {
//        isValid(LocalDate.now().minusYears(18));
//    }
}
