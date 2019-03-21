package com.bytrees.job;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.bytrees.service.repository"})
@EntityScan({"com.bytrees.service.entity"})
public class Application {
    public static void main(String[] argv) {
    	
    	System.out.println("job finished.");
    }
}
