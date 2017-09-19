package com.imd.friendsManagement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Gilang ZW
 *
 */
@ComponentScan
@SpringBootApplication
public class SpringBootWebApplication  extends SpringBootServletInitializer implements CommandLineRunner{
    
    private static final Logger logger = LoggerFactory.getLogger(SpringBootWebApplication.class);
    
    @Autowired
    DataSource dataSource;
    
    public static void main(String[] args) {
        try{
            SpringApplication.run(SpringBootWebApplication.class, args);
            logger.info("Server connected...");
        } catch (Exception ex){
            logger.info("Server disconnected cause... {}", ex);
        }

    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("DATASOURCE = {}", dataSource);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }
}