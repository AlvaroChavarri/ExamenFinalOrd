package com.vaadin.vaadinarchetypeapplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApp {
    private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApp.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudWithVaadinApp.class);
    }

    @Bean
    public CommandLineRunner loadData(IPRepository repository) {
        return (args) -> {
            // save customers
            repository.save(new IP("00000", "Spain","Madrid","Pozuelo","20.0", "30.0","12121","14:15"));

            // fetch all customers
            log.info("IP found with findAll():");
            log.info("-------------------------------");
            for (IP ip : repository.findAll()) {
                log.info(ip.toString());
            }
            log.info("");


            IP ip = repository.findById(1L).get();
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(ip.toString());
            log.info("");


            log.info("IP found with findByCountryCodeStartsWithIgnoreCase('00000'):");
            log.info("--------------------------------------------");
            log.info("");
        };
    }

}
