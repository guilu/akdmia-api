package com.diegobarrioh.api.akdmiaapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
@Info(
        title = "Akdemia",
        version = "0.1",
        description = "Akdemia API. This API manages the backend data, creation/modification/deletion of groups,units,questions,answers and exams.",
        license = @License(name = "Apache 2.0", url = "http://foo.bar"),
        contact = @Contact(url = "http://diegobarrioh.dev", name = "Diego", email = "diegobarrioh@gmail.com")
)
)
public class OpenApiConfiguration {


}
