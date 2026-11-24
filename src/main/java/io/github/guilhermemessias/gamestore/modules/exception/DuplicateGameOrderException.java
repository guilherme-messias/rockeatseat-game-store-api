package io.github.guilhermemessias.gamestore.modules.exception;

public class DuplicateGameOrderException extends IllegalArgumentException{
    public DuplicateGameOrderException(String message) {
        super(message);
    }
}
