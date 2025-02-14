package helperbot.exceptions;

/**
 * Represents a custom exception class for HelperBot.
 * <p> The {@code HelperBotException} class is a custom exception
 * that extends {@link RuntimeException}. This is used to handle
 * errors within the chatbot.</p>
 */
public class HelperBotException extends RuntimeException {
    public HelperBotException(String message) {
        super(message);
    }
}
