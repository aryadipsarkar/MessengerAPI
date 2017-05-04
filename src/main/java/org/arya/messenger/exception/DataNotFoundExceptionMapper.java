package org.arya.messenger.exception;

import org.arya.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    public Response toResponse(DataNotFoundException e) {
        /**
         * This method takes in a response and it returns a response
         * So whenever exception is thrown in the application, JAX-RS calls this method. This will pass the exception thrown through
         * this method and in turn get the custom response and send it back to the client developer
         */
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),404,"https://jersey.java.net/download.html");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
