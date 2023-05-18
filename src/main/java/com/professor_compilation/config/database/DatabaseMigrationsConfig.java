package com.professor_compilation.config.database;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Config for data base migrations
 */
@Configuration
public class DatabaseMigrationsConfig {

    /**
     * DataSource
     */
    private final DataSource dataSource;

    /**
     * public construction DatabaseMigrationsConfig
     * @param dataSource - dataSource
     */
    public DatabaseMigrationsConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Bean for creating migrations
     * @return Flyway
     */
    @Bean
    public Flyway flyway() {
        //  log.info("Creatives - Flyway: starting to look for and executing new migrations...");
        Flyway flyway = Flyway
                .configure()
                .dataSource(this.dataSource)
                .table("flyway_schema")
                .baselineOnMigrate(true)
                .locations("db/migrations")
                .load();
        try {
            flyway.migrate();
        } catch (FlywayException e) {
            //  log.error(e.getLocalizedMessage());
            flyway.repair();
            flyway.migrate();
        }
        //  log.info("Creatives - Flyway: work finished ...");

        return flyway;
    }
}
