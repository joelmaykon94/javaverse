package profiles;

import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

public class IntegrationTestContainerProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.db-kind", "postgresql",
                "quarkus.flyway.migrate-at-start", "true"
        );
    }

    @Override
    public String getConfigProfile() {
        return "testcontainers";
    }
}
