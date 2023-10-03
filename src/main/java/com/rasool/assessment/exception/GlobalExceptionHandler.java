package com.rasool.assessment.exception;



import com.rasool.assessment.payload.common.ErrorResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.UNAUTHORIZED, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.NOT_FOUND, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommonServerException.class)
    public final ResponseEntity<Object> handleCommonServerException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataConflictException.class)
    public final ResponseEntity<Object> handleDataConflictException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.CONFLICT, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.CONFLICT);
    }
}
