package org.arya.messenger.exception;
import org.arya.messenger.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable e) {
        /**
         * Throwable acts as a blanket exception! No matter whatever error is thrown, it will get caught by Throwable
         * and will be caught here
         */
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),404,"https://jersey.java.net/download.html");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}