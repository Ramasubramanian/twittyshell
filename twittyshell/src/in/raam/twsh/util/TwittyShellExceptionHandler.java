package in.raam.twsh.util;

/**
 * Common exception/error handler for the application
 * @author raam
 *
 */
public class TwittyShellExceptionHandler {

    public static void handleException(Exception e) {
        //just print as of now
        e.printStackTrace();
    }
    
    public static void handleError(Error e) {
        //do nothing
    }
    
}
