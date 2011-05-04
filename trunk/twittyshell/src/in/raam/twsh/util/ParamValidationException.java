package in.raam.twsh.util;

/**
 * Exception to indicate that a Twitter request parameter validation has failed
 * @author raam
 *
 */
public class ParamValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    ParamValidationException() {
        super();
    }

    public ParamValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamValidationException(String message) {
        super(message);
    }

    ParamValidationException(Throwable cause) {
        super(cause);
    }
    
    

}
