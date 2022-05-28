package org.billing.api.app.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.billing.api.repository")
public class DbConfiguration {
}
