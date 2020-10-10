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
            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers");

    @ArchTest
    static final ArchRule teste = ArchRuleDefinition
        .classes().that().haveSimpleNameEndingWith("Service")
        .should().resideInAPackage("com.zup.bootcamp.services");
}
