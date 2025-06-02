package profiles;

import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

public class UnitWithFlywayProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.db-kind", "h2",
                "quarkus.datasource.jdbc.url", "jdbc:h2:mem:flywaydb;DB_CLOSE_DELAY=-1",
                "quarkus.flyway.migrate-at-start", "true"
        );
    }

    @Override
    public String getConfigProfile() {
        return "unit-flyway";
    }
}
