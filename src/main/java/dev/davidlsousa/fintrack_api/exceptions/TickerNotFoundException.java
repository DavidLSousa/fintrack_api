package dev.davidlsousa.fintrack_api.exceptions;

public class TickerNotFoundException extends RuntimeException {

    public TickerNotFoundException(String ticker) {
        super("Ticker não encontrado: " + ticker);
    }
}
