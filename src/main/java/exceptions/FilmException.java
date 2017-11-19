package exceptions;
/**
 * gere les erreurs liees aux films et leur recuperation (detail etc ..)
 */
public class FilmException extends Exception {
    public FilmException(String message) {
        super(message);
    }
}
