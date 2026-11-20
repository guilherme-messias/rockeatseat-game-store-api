package io.github.guilhermemessias.gamestore.modules.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String fieldName;
    private String errorMessage;
}
