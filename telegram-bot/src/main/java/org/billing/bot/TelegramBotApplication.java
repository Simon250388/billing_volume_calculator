package org.billing.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class TelegramBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class, args);
    }
}
