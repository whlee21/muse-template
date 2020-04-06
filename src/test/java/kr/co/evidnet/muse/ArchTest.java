package kr.co.evidnet.muse;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("kr.co.evidnet.muse");

        noClasses()
            .that()
                .resideInAnyPackage("kr.co.evidnet.muse.service..")
            .or()
                .resideInAnyPackage("kr.co.evidnet.muse.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..kr.co.evidnet.muse.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
