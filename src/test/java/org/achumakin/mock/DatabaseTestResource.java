package org.achumakin.mock;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class DatabaseTestResource implements QuarkusTestResourceLifecycleManager {

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
            new PostgreSQLContainer<>("postgres:13")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test");

    @Override
    public Map<String, String> start() {
        POSTGRES_CONTAINER.start();
        return Map.of(
                "quarkus.datasource.jdbc.url", POSTGRES_CONTAINER.getJdbcUrl(),
                "quarkus.datasource.username", POSTGRES_CONTAINER.getUsername(),
                "quarkus.datasource.password", POSTGRES_CONTAINER.getPassword(),
                "quarkus.datasource.driver", POSTGRES_CONTAINER.getDriverClassName()
        );
    }

    @Override
    public void stop() {
        POSTGRES_CONTAINER.stop();
    }
}

