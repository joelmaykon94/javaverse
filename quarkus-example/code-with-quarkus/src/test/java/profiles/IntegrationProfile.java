package profiles;

import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

public class IntegrationProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.db-kind", "postgresql",
                "quarkus.datasource.jdbc.url", "jdbc:postgresql://localhost:5432/testdb",
                "quarkus.datasource.username", "testuser",
                "quarkus.datasource.password", "testpass",
                "quarkus.flyway.migrate-at-start", "true"
        );
    }

    @Override
    public String getConfigProfile() {
        return "integration";
    }
}
