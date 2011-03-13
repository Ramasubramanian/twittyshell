package in.raam.twsh.oauth;

/**
 * Exception thrown when a user is already authorized and using the system meanwhile another user is trying to login
 * without the previous user logging out
 * @author raam
 *
 */
public class AlreadyAuthorizedException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    AlreadyAuthorizedException() {
        super();
        // TODO Auto-generated constructor stub
    }

    AlreadyAuthorizedException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    AlreadyAuthorizedException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    AlreadyAuthorizedException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
