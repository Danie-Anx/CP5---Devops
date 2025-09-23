package com.moneyhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiDimdimApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDimdimApplication.class, args);
    }
}

@RestController
class HelloController {
    @GetMapping("/")
    public String hello() {
        return "🚀 API DimDim rodando com sucesso (Spring Boot 3 + Java 17)!";
    }
}
