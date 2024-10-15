package lk.ijse.gdse68.nike_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public String healthCheck() {
        System.out.println("Spring POS API is running");
        return "Spring POS API is running";
    }

}