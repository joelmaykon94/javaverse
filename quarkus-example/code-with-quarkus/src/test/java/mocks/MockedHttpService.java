package mocks;

import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.context.ApplicationScoped;

@Alternative
@ApplicationScoped
public class MockedHttpService implements ExternalHttpService { // Substitua HttpServiceInterface pela interface apropriada
    @Override
    public String callExternalService() {
        return "Mock response";
    }
}
