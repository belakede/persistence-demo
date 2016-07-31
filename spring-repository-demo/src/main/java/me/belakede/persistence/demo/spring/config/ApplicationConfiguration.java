package me.belakede.persistence.demo.spring.config;

import me.belakede.persistence.demo.spring.Demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"me.belakede.persistence.demo.spring.config"})
public class ApplicationConfiguration {

    @Bean
    public Demo demo() {
        return new Demo();
    }

}
