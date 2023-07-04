package com.education.projects.cars.manager.carsmanager;

import com.education.projects.cars.manager.carsmanager.service.DialogMenuService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AutoManagerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run (AutoManagerApplication.class, args);
        DialogMenuService dialogMenuService = context.getBean("dialogMenuService", DialogMenuService.class);
        dialogMenuService.chooseAction();
    }
}
