package io.github.guilhermemessias.gamestore.modules.exception;

public class ExistingClient extends IllegalArgumentException{
    public ExistingClient(String message) {
        super(message);
    }
}
