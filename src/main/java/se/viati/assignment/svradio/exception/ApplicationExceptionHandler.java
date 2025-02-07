package se.viati.assignment.svradio.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<PlaylistErrorResponse> handleClientErrorException(
            HttpClientErrorException ex, WebRequest request) {
        PlaylistErrorResponse errorResponse = buildErrorResponse(ex, request)
                .status(ex.getStatusCode().value())
                .error(ex.getStatusCode().toString())
                .build();
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<PlaylistErrorResponse> handleRestOfExceptions(
            RuntimeException ex, WebRequest request) {
        PlaylistErrorResponse errorResponse = buildErrorResponse(ex, request)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        log.error("Encountered exception of type {} when using service {} short stack trace\n {}",
                ex.getClass().getName(), errorResponse.path(), ExceptionUtils.getShortStackTrace(ex));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private PlaylistErrorResponse.PlaylistErrorResponseBuilder buildErrorResponse(RuntimeException ex, WebRequest request) {
        String message = Objects.nonNull(ex.getMessage()) ? ex.getMessage() : "Failed to fetch the data";
        return PlaylistErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .path(((ServletWebRequest) request).getRequest().getRequestURI());
    }
}
