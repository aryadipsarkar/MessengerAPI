package org.arya.messenger.security;

import org.glassfish.jersey.internal.util.Base64;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * authorizing the "profiles" with username as "user" and password as "pass"
 * "profiles" will be accessed with secured/profiles/
 */
@Provider
public class SecuredResource implements ContainerRequestFilter {
    private static final String AUTH_HEADER_KEY= "Authorization";
    private static final String AUTH_HEADER_PREFIX= "Basic ";
    private static final String SECURED_URL_PREFIX="secured";

    public void filter(ContainerRequestContext requestContext) throws IOException {
    if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){
        List<String> authHeader= requestContext.getHeaders().get(AUTH_HEADER_KEY);
        if (authHeader.size()>0){
            String authToken= authHeader.get(0);
            authToken=authToken.replaceFirst(AUTH_HEADER_PREFIX,"");
            String decodedString= Base64.decodeAsString(authToken);
            StringTokenizer tokenizer= new StringTokenizer(decodedString,":");
            String username= tokenizer.nextToken();
            String password= tokenizer.nextToken();

            if (username.equals("user") && password.equals("pass")){
                return;
            }
        }
        Response unauthorizedStatus= Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("USer cannot access this API")
                .build();
        requestContext.abortWith(unauthorizedStatus);
    }
    }
}
