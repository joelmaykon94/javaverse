package controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import profiles.IntegrationTestContainerProfile;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestProfile(IntegrationTestContainerProfile.class)
@Testcontainers
public class ProductControllerIT {

    @Container
    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @BeforeAll
    static void setup() {
        System.setProperty("quarkus.datasource.jdbc.url", postgres.getJdbcUrl());
        System.setProperty("quarkus.datasource.username", postgres.getUsername());
        System.setProperty("quarkus.datasource.password", postgres.getPassword());
    }

    @Test
    public void testListaProdutos() {
        given()
                .when().get("/produtos")
                .then()
                .statusCode(200)
                .body("$.size()", is(3)); // exemplo
    }
}
