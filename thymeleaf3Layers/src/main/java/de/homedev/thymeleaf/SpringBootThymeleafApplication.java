package de.homedev.thymeleaf;

import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import de.homedev.thymeleaf.api.fassade.IPersonFassade;
import de.homedev.thymeleaf.api.fassade.IUserFassade;
import de.homedev.thymeleaf.backend.config.DbConfig;
import de.homedev.thymeleaf.backend.util.RmiServerUtils;
import de.homedev.thymeleaf.frontend.config.ApplicationSecurityConfig;
import de.homedev.thymeleaf.frontend.config.WebMvcConfiguration;
import de.homedev.thymeleaf.frontend.util.RmiClientUtil;

@SpringBootApplication
@Import({ DbConfig.class, WebMvcConfiguration.class, ApplicationSecurityConfig.class })
@ComponentScan(basePackages = { "de.homedev.thymeleaf" })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
@EnableWebSecurity
public class SpringBootThymeleafApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootThymeleafApplication.class);

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootThymeleafApplication.class, args);
			int port = Integer.valueOf(ctx.getEnvironment().getRequiredProperty("app.rmi.port"));
			log.info("rmi server port: " + port);

			// Start RMI Server
			Registry registry = RmiServerUtils.createRegistry(port);
			IUserFassade userFassade = (IUserFassade) ctx.getBean(IUserFassade.SERVICE_NAME);
			RmiServerUtils.bindUserFassade(registry, userFassade);
			IPersonFassade personFassade = (IPersonFassade) ctx.getBean(IPersonFassade.SERVICE_NAME);
			RmiServerUtils.bindPersonFassade(registry, personFassade);

			// Read from RMI Server
			RmiClientUtil.getUserFassade(port, "localhost");
			RmiClientUtil.getPersonFassade(port, "localhost");

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

}
