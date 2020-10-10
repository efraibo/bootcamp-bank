package com.zup.bootcamp.architecture.controller;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.zup.bootcamp.controllers", importOptions = {
        ImportOption.DoNotIncludeJars.class })
public class ControllerRegrasTest {

    @ArchTest
    public static final ArchRule controllers_deve_contem_annotation_restcontroller = ArchRuleDefinition//
            .classes().that().resideInAPackage("com.zup.bootcamp.controllers")//
            .should().beAnnotatedWith(RestController.class)//
            .because("controllers deve conter annotation RestController");//

    //não está funcionando corretamente
    @ArchTest
    public static final ArchRule controllers_nao_deve_conter_annotation_autowired_apenas_contrutores = ArchRuleDefinition
            .classes().should().notBeAnnotatedWith(Autowired.class)//
            .because("controllers não pode conter Autowired. Deve ser injetado com contrutores");//

}
