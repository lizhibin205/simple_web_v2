package com.bytrees.job;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bytrees.service.entity.Goods;
import com.bytrees.service.repository.GoodsRepository;

@SpringBootApplication
@EnableJpaRepositories({"com.bytrees.service.repository"})
@EntityScan({"com.bytrees.service.entity"})
public class Application {

    public static void main(String[] argv) {
    	SpringApplication.run(Application.class, argv);
    	//System.out.println("job finished.");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    	return args -> {
	    	GoodsRepository goodsRepository = ctx.getBean(GoodsRepository.class);
	    	Optional<Goods> goods = goodsRepository.findById(1L);
	    	if (goods.isPresent()) {
	    		System.out.println("find goods name=" + goods.get().getName());
	    	} else {
	    		System.out.println("no goods");
	    	}
	    	System.out.println("job finished.");
    	};
    }
}
