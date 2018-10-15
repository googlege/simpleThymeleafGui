package de.homedev.thymeleaf.simplegui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import de.homedev.thymeleaf.simplegui.config.DbConfig;

@SpringBootApplication
@Import({ DbConfig.class })
@ComponentScan(basePackages = { "de.homedev.thymeleaf.simplegui" })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootThymeleafApplication.class, args);

	}

}
