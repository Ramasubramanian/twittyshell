package in.raam.twsh.command.impl;

import in.raam.twsh.main.TwittyShell;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.util.List;

/**
 * Twitter command implementation to facilitate OAuth based authentication for the user
 * @author raam
 *
 */
public class LoginCommand extends AbstractTwitterCommand {

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        try {
            if(args.length == 0) // if simple login then prompt for authorization                
                OAuthConsumerHolder.authorize("DUMMY");
            else //if user name is provided search in store
                OAuthConsumerHolder.authorize(args[0]);
            TwittyShell.addUser(args[0]);
            return Util.newList("Logged in and authorized!");
        } catch (Exception e) {
            TwittyShellExceptionHandler.handleException(e);
            return Util.newList("Error occured while authorising");
        }
    }

}
