package io.taech.triple.common.excpeted;

import io.taech.triple.common.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, ValidateException.class})
    public ResponseEntity<ErrorResponse> handleValidationExceptions(final Exception e) {

        final ServiceStatus serviceStatus = ServiceStatus.INVALID_REQUEST;
        log.info("[{}]Exception Handler detected this: ", e.getClass().getSimpleName(), e);

        return ResponseEntity
                .status(serviceStatus.getStatus())
                .body(ErrorResponse.create(serviceStatus));
    }

    @ExceptionHandler(EventProcessingException.class)
    public ResponseEntity<ErrorResponse> handleEventProcessExceptions(final EventProcessingException e) {

        final ServiceStatus serviceStatus = e.getServiceStatus();
        log.info("[{}]Exception Handler detected this: ", e.getClass().getSimpleName(), e);

        return ResponseEntity
                .status(serviceStatus.getStatus())
                .body(ErrorResponse.create(serviceStatus));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {

        final ServiceStatus serviceStatus = ServiceStatus.SYSTEM_ISSUE;
        log.info("[{}]Exception Handler detected this: ", e.getClass().getSimpleName(), e);

        return ResponseEntity
                .status(serviceStatus.getStatus())
                .body(ErrorResponse.create(serviceStatus));
    }

}
