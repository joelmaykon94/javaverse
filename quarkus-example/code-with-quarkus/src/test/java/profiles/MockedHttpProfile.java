package profiles;

import io.quarkus.test.junit.QuarkusTestProfile;
import mocks.MockedHttpService;

import java.util.Map;
import java.util.Set;

public class MockedHttpProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.db-kind", "h2",
                "quarkus.datasource.jdbc.url", "jdbc:h2:mem:mockdb;DB_CLOSE_DELAY=-1",
                "quarkus.flyway.migrate-at-start", "true",
                "external.service.base-url", "http://localhost:8089" // WireMock
        );
    }

    @Override
    public Set<Class<?>> getEnabledAlternatives() {
        return Set.of(MockedHttpService.class); // @Alternative ou @MockBean
    }

    @Override
    public String getConfigProfile() {
        return "mock-http";
    }
}
