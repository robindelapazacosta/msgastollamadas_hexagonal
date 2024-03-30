package com.provari.llamadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LlamadasApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlamadasApplication.class, args);
    }

}
