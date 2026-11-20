package io.github.guilhermemessias.gamestore.modules.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    public ExceptionHandlerController(MessageSource messageSource) {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorMessageDTO> errorMessages = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(new ErrorMessageDTO(fieldName, errorMessage));
        });

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

}
