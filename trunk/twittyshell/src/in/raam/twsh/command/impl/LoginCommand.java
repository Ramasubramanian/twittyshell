package in.raam.twsh.command.impl;

import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.Util;

import java.util.List;

/**
 * Twitter command implementation to facilitate OAuth based authentication for the user
 * @author raam
 *
 */
public class LoginCommand extends AbstractTwitterCommand {

/*    static final String[] required = { "username" };

    protected String[] getRequired() {
        return required;
    }*/

    @Override
    public List<String> execute(String[] args) {
        try {
            if(args.length == 0) // if simple login then prompt for authorization                
                OAuthConsumerHolder.authorize("DUMMY");
            else //if user name is provided search in store
                OAuthConsumerHolder.authorize(args[0]);
            return Util.newList("Logged in and authorized!");
        } catch (Exception e) {
            e.printStackTrace();
            return Util.newList("Error occured while authorising");
        }
    }

}
