package org.billing.rooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RoomsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoomsApplication.class, args);
    }
}
