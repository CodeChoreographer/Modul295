package com.modul295_lb1.productmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.modul295_lb1.productmanager",
        "com.modul295_lb1.productmanager.auth"
})
public class ProductManager {

    public static void main(String[] args) {
        SpringApplication.run(ProductManager.class, args);
    }

}
