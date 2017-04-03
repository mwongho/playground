package com.company.spring_speedment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.company.spring_speedment.db0.spring_speedment.customer.CustomerController;
import com.company.spring_speedment.db0.spring_speedment.customer.CustomerManager;

@SpringBootApplication(scanBasePackageClasses=CustomerController.class)
public class Application {
    private @Value("${dbms.username}") String username;
    private @Value("${dbms.password}") String password;
    private @Value("${dbms.schema}") String schema;
	
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public SpringSpeedmentApplication getApplication() {
        return new SpringSpeedmentApplicationBuilder()
            .withUsername(username)
            .withPassword(password)
            .withSchema(schema)
            .build();
    }

    // Individual managers
    @Bean
    public CustomerManager getCustomerManager(SpringSpeedmentApplication app) {
        return app.getOrThrow(CustomerManager.class);
    }
}
