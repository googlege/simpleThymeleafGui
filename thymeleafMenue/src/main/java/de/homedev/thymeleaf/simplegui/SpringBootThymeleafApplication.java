package de.homedev.thymeleaf.simplegui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import de.homedev.thymeleaf.simplegui.config.ApplicationSecurityConfig;
import de.homedev.thymeleaf.simplegui.config.DbConfig;
import de.homedev.thymeleaf.simplegui.config.WebMvcConfiguration;

@SpringBootApplication
@Import({ DbConfig.class, WebMvcConfiguration.class, ApplicationSecurityConfig.class })
@ComponentScan(basePackages = { "de.homedev.thymeleaf.simplegui" })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
@EnableWebSecurity
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootThymeleafApplication.class, args);

	}

}
