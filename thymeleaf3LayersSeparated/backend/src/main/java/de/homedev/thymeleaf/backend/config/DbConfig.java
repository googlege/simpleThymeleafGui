
package de.homedev.thymeleaf.backend.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Configuration
@ComponentScan(basePackages = { "de.homedev.thymeleaf" })
@EntityScan(value = { "de.homedev.thymeleaf.api.model" })
@EnableJpaRepositories(basePackages = { "de.homedev.thymeleaf.backend.dao" })
@EnableTransactionManagement

public class DbConfig {
	private static Logger LOGGER = LoggerFactory.getLogger(DbConfig.class);

	@Primary
	@Bean(name = "dataSource")
	@FlywayDataSource
	public DataSource getDataSourceProperties(final Environment environment) throws IOException {
		final int port = Integer.valueOf(environment.getRequiredProperty("app.datasource.port"));
		final String database = environment.getRequiredProperty("app.datasource.database");
		String username = environment.getRequiredProperty("app.datasource.username");
		String password = environment.getRequiredProperty("app.datasource.password");
		EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.builder().setPort(port).setCleanDataDirectory(true).start();
		if (embeddedPostgres != null) {
			LOGGER.info("Embedded postgre SQL started");
		} else {
			LOGGER.info("Embedded postgre SQL not started");
		}

		LOGGER.info("creating database " + database);

		try (Connection conn = embeddedPostgres.getPostgresDatabase().getConnection()) {
			Statement statement = conn.createStatement();
			if (!hasDatabase(database, statement)) {
				statement.execute("CREATE DATABASE " + database);
				LOGGER.info("database " + database + " created");
			}
			if (!hasUser("postgres", statement)) {
				statement.execute("CREATE USER " + username + " WITH ENCRYPTED PASSWORD '" + password + "'");
				statement.execute("GRANT ALL PRIVILEGES ON DATABASE \"" + database + "\" to " + username);
				LOGGER.info("user " + username + " with all privileges on database " + database + " created");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return embeddedPostgres.getDatabase(username, database);

	}

	private boolean hasDatabase(String database, Statement statement) throws SQLException {
		ResultSet r = statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname ='" + database + "\'");
		long result = 0;
		if (r.next()) {
			result = r.getLong(1);
		}
		return (result == 1l);
	}

	private boolean hasUser(String user, Statement statement) throws SQLException {
		ResultSet r = statement.executeQuery("SELECT count(*) FROM pg_user WHERE usename ='" + user + "\'");
		long result = 0;
		if (r.next()) {
			result = r.getLong(1);
		}
		return (result == 1l);
	}

}
