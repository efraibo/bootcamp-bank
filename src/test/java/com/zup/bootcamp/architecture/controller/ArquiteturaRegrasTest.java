package com.zup.bootcamp.architecture.controller;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.zup.bootcamp.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.zup.bootcamp",
        importOptions = { ImportOption.DoNotIncludeJars.class })
public class ArquiteturaRegrasTest {

    @ArchTest
    public static final ArchRule controllers_deve_contem_annotation_restcontroller = ArchRuleDefinition//
            .classes().that().resideInAPackage("com.zup.bootcamp.controllers")//
            .should().beAnnotatedWith(RestController.class)//
            .because("controllers deve conter annotation RestController");//

    @ArchTest
    public static final ArchRule services_deve_contem_annotation_service = ArchRuleDefinition//
            .classes().that().resideInAPackage("com.zup.bootcamp.services")//
            .should().beAnnotatedWith(Service.class)//
            .because("services deve conter annotation Service");//

    @ArchTest
    static final ArchRule dependencias_camadas_sao_respeitadas = layeredArchitecture()
            .layer("Controllers").definedBy("..controllers..")
            .layer("Services").definedBy("..services..")
            .layer("Validations").definedBy("..validations..")
            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers", "Validations")
            .because("Controllers não pode ser acessado por outra camada, " +
                    "Services podem ser acessadas apenas pelo controller");

    @ArchTest
    static final ArchRule pacotes_services_devem_ser_respeitados = ArchRuleDefinition
        .classes().that().haveSimpleNameEndingWith("Service")
        .should().resideInAPackage("..services..")
            .because("Classes com terminologia Service devem está dentro do pacote com.zup.bootcamp.services");

    @ArchTest
    static final ArchRule pacotes_controllers_devem_ser_respeitados = ArchRuleDefinition
            .classes().that().haveSimpleNameEndingWith("Controller")
            .should().resideInAPackage("..controllers..")
            .because("Classes com terminologia Controller devem está dentro do pacote com.zup.bootcamp.controllers");

    @ArchTest
    static final ArchRule pacotes_repositories_devem_ser_respeitados = ArchRuleDefinition
            .classes().that().haveSimpleNameEndingWith("Repository")
            .should().resideInAPackage("..repositories..")
            .because("Classes com terminologia Repository devem está dentro do pacote com.zup.bootcamp.repositories");
}
