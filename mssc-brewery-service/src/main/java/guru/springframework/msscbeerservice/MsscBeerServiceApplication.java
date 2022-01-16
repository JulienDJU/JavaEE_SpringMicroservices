package guru.springframework.msscbeerservice;

import guru.sfg.brewery.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MsscBeerServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerServiceApplication.class, args);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        String[] beans = applicationContext.getBeanDefinitionNames();

        for (String bean: beans) {
            //System.out.println("Bean name: " + bean);
        }

        BeerDto beerDto = new BeerDto();
        System.out.println("Beer dto: " + beerDto);
        beerDto.setBeerName("");
        System.out.println("Beer dto: " + beerDto);

    }
}
