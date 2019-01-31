package sdek.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 * Created by alex on 07.04.17.
 */
//@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
//@EntityScan("aosivt.Entities")
public class SpringApplicationInit {


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationInit.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry

                        .addMapping("/*")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedHeaders("Access-Control-Allow-Origin", "*")
                        .maxAge(3600)
                ;
            }
        };
    }
}
