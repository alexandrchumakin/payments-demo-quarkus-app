package org.achumakin.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.achumakin.data.UserRepositoryImpl;

import java.util.Base64;
import java.util.Map;


@Provider
@Priority(1) // Set a high priority to ensure this filter is executed first
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    UserRepositoryImpl userRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Implement your authorization logic here
        if (!isAuthorized(requestContext)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    public boolean isAuthorized(ContainerRequestContext requestContext) {
        var authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null) {
            requestContext.abortWith(Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Authorization header is required"))
                    .build());

            return false;
        }

        var basicAuth = new String(Base64.getDecoder().decode(authHeader.substring(authHeader.indexOf("Basic ") + 6)));
        var user = basicAuth.split(":")[0];
        var password = basicAuth.split(":")[1];
        return credentialsExist(user, password);
    }

    public boolean credentialsExist(String user, String password) {
        return userRepository
                .find("name = ?1 and password = ?2", user, password)
                .count() > 0;
    }

}
