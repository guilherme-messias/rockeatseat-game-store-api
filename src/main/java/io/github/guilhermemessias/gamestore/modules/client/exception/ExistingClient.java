package io.github.guilhermemessias.gamestore.modules.client.exception;

public class ExistingClient extends IllegalArgumentException{
    public ExistingClient(String message) {
        super(message);
    }
}
