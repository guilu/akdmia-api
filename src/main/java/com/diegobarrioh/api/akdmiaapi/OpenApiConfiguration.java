package com.diegobarrioh.api.akdmiaapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
@Info(
        title = "Akdmia",
        version = "0.1",
        description = "Akdmia API. <br /><br /> This API manages the backend data, creation/modification/deletion of groups, units, questions, answers and exams.",
        license = @License(name = "Apache 2.0", url = "http://foo.bar"),
        contact = @Contact(url = "http://diegobarrioh.dev", name = "Author: Diego Barrio Hortigüela", email = "diegobarrioh@gmail.com")
),
        security = @SecurityRequirement(name = "Authentication")
)
@SecurityScheme(
        name = "Authentication",
        description = "Botón Authorize de la página de openApi (swagger)",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi apiVersion1() {
        return getVersion("1.0", "com.diegobarrioh.api.akdmiaapi.controller.v1");
    }

    @Bean
    public GroupedOpenApi apiVersion2() {
        return getVersion("2.0", "com.diegobarrioh.api.akdmiaapi.controller.v2");
    }

    private GroupedOpenApi getVersion(String version, String basePackage) {
        return GroupedOpenApi.builder()
                .group(version)
                .packagesToScan(basePackage)
                .build();
    }

}
