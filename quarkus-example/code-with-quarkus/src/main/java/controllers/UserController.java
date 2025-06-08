package controllers;

import dto.LoginDto;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import services.TokenService;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/user")
@Authenticated
@Tag(name = " user", description = "All the user methods")
public class UserController {
    @Inject
    JsonWebToken jwt;

    @Inject
    TokenService tokenService;

    @GET()
    @Path("/current/info-alternative")
    public Principal getCurrentUserInfoAlternative(@Context SecurityContext ctx) {
        return ctx.getUserPrincipal();
    }

    @POST
    @PermitAll
    @Path("/access-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccessToken(LoginDto loginRequest) throws IOException, InterruptedException {
        return tokenService.getAccessToken(loginRequest.username, loginRequest.password);
    }

    @GET
    @Path("/current/info")
    public JsonWebToken getCurrentUserInfo() {
        return jwt;
    }

    @GET
    @Path("/current/info/claims")
    public Map<String, Object> getCurrentUserInfoClaims() {
        return jwt.getClaimNames().stream().map(name -> Map.entry(name, jwt.getClaim(name))).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }
}
