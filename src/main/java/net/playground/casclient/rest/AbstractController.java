package net.playground.casclient.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController {

    @ExceptionHandler
    protected void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "The request was not valid");
    }

    @ExceptionHandler
    protected void handleInternalException(RestApplicationException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An internal server-error caused the request to not be processed");
    }

}
