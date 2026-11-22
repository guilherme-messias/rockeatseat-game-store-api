package io.github.guilhermemessias.gamestore.modules.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO("message", "Corpo da requisição inválido" +
                " ou malformado. Verifique a sintaxe do JSON e os tipos dos campos enviados.");
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO("message", ex.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO("message", "O valor informado" +
                " é inválido ou não corresponde a nenhuma opção permitida.");
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
