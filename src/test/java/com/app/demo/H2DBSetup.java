package com.app.demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.io.IOException;

public class H2DBSetup {

    private static boolean initialized;
    private static DataSource globalDataSource;

    @BeforeClass
    public static void databaseSetup() throws IOException {
        if (!initialized) {
            globalDataSource = getGlobalDataSource();
            executeScript();
            initialized = true;
        }
    }

    private static void executeScript() {
        JdbcTemplate globalJdbcTemplate = new JdbcTemplate(globalDataSource);

        JdbcTestUtils.executeSqlScript(globalJdbcTemplate,
                new EncodedResource(new ClassPathResource("schema.sql")), true);
    }

    private static DataSource getGlobalDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:global;DB_CLOSE_DELAY=-1");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @AfterClass
    public static void cleanDatabase() {
        executeScript();
    }
}
