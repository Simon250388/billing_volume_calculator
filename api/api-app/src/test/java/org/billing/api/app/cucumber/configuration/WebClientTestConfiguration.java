package org.billing.api.app.cucumber.configuration;

import org.billing.api.client.configuration.WebClientConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.billing.api.client")
@Import(WebClientConfiguration.class)
public class WebClientTestConfiguration {
}
