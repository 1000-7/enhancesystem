package org.whusim.enhancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class EnhancesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnhancesystemApplication.class, args);
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
