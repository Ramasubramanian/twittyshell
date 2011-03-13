package in.raam.twsh.command;

import java.util.List;

/**
 * Common specification for the object representing user commands entered in the
 * console by User
 * @author raam
 *
 */
public interface TwitterCommand {

    /**
     * Execute this command and provide the appropriate output to the user
     * @param args
     *              Arguments required for this command execution provided by the user in console
     * @return
     */
    public List<String> execute(String[] args);
    
    /**
     * Validate the list of arguments and return a boolean appropriately
     * @param args
     *              Arguments required for this command execution provided by the user in console
     * @return
     */
    public boolean validateArgs(String[] args);
    
    /**
     * Returns the message to be displayed when the argument validations fail for the command
     * @param command
     *                  Command String
     * @return
     */
    public String getMessage(String command);
}
