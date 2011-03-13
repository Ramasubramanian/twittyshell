package in.raam.twsh.command.impl;

import in.raam.twsh.command.TwitterCommand;

/**
 * Super class of all twitter commands providing some convenience methods and default implementation
 * for few methods
 * @author raam
 *
 */
public abstract class AbstractTwitterCommand implements TwitterCommand {

    protected String[] getRequired() {
        return new String[0];
    }
    
    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#validateArgs(java.lang.String[])
     */
    public boolean validateArgs(String[] args) {
        String[] required = getRequired();
        if (args == null || required == null)
            return false;
        return args.length >= required.length;
    }

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#getMessage(java.lang.String)
     */
    public String getMessage(String command) {
        String[] required = getRequired();
        StringBuilder sb = new StringBuilder(
                "Invalid Usage! Correct format is : ");
        sb.append(command + " ");
        for (String s : required)
            sb.append("<" + s + "> ");
        return sb.toString();
    }

}
