package guru.sfg.beer.inventory.service;

import guru.sfg.beer.inventory.service.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class BeerInventoryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BeerInventoryApplication.class, args);
    }

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        Config config = applicationContext.getBean(Config.class);
//        System.out.println(config.getOnlyOnConfigServer());
    }
}
