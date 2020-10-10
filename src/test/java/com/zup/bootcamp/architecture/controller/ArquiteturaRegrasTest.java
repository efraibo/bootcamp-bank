package com.zup.bootcamp.architecture.controller;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.zup.bootcamp",
        importOptions = { ImportOption.DoNotIncludeJars.class })
public class ArquiteturaRegrasTest {

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


//    @ArchTest
//    public ArchRule controller_public_methods_should_return = methods()
//            .that().areDeclaredInClassesThat().resideInAPackage("..controllers..")
//            .and().arePublic().should().haveRawReturnType(ResponseEntity.class)
//            .because("here is the explanation");
}
