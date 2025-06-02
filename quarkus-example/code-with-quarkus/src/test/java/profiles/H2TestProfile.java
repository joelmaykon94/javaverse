package profiles;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

public class H2TestProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.db-kind", "h2",
                "quarkus.datasource.jdbc.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                "quarkus.datasource.username", "sa",
                "quarkus.datasource.password", "sa",
                "quarkus.flyway.migrate-at-start", "false"
        );
    }

    @Override
    public String getConfigProfile() {
        return "test-h2";
    }
}
