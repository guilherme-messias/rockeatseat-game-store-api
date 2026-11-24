package io.github.guilhermemessias.gamestore.modules.exception;

public class ExistingGame extends IllegalArgumentException{
    public ExistingGame(String message) {
        super(message);
    }
}
