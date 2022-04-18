package guru.sfg.beer.inventory.service.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
//@Profile("local")
@Configuration
@Data
public class Config {

//    @Value("${only-on-config-server}")
//    private String onlyOnConfigServer;

}
