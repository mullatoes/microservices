package com.pension.benefits_exitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BenefitsExitsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenefitsExitsServiceApplication.class, args);
    }

}
