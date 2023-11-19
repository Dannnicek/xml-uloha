package com.idea;

import com.idea.service.XMLService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.net.MalformedURLException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws MalformedURLException {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        XMLService dataProcessingService = context.getBean(XMLService.class);
        dataProcessingService.processData();
    }
}