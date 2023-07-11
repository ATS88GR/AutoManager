package com.education.projects.cars.manager.carsmanager.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Scanner;
@Service
@Getter
public class ScannerService {
    private final Scanner sc;
    public ScannerService(){
        sc = new Scanner(System.in);
    }
    @PreDestroy
    public void closeScanner(){
        sc.close();
        System.out.println("Scanner closed");
    }
}
