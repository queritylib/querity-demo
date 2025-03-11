package net.brunomendola.querity.demo.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Querity - DEMO",
    description = "Demo project for the Querity library"))
public class ApiDocsConfig {
}
