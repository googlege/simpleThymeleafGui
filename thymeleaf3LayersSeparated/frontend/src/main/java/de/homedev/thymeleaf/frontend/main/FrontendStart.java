
package de.homedev.thymeleaf.frontend.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import de.homedev.thymeleaf.frontend.config.ApplicationSecurityConfig;
import de.homedev.thymeleaf.frontend.config.WebMvcConfiguration;
import de.homedev.thymeleaf.frontend.util.RmiClientUtil;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "de.homedev.thymeleaf.frontend" })
@Import({ WebMvcConfiguration.class, ApplicationSecurityConfig.class })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
@EnableWebSecurity
public class FrontendStart {
	private static final Logger log = LoggerFactory.getLogger(FrontendStart.class);

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext ctx = SpringApplication.run(FrontendStart.class, args);
			int port = Integer.valueOf(ctx.getEnvironment().getRequiredProperty("app.rmi.port"));
			String host = ctx.getEnvironment().getRequiredProperty("app.rmi.host");
			log.info("rmi server port: " + port);

			// Read from RMI Server
			RmiClientUtil.getUserFassade(port, host);
			RmiClientUtil.getPersonFassade(port, host);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
