package in.raam.twsh.command.impl;

import in.raam.twsh.oauth.AccessKeyStore;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.Util;

import java.util.List;

/**
 * Twitter command implementation to logout the current User, users will have to authorize once again after
 * logging out
 * @author raam
 * 
 */
public class LogoutCommand extends AbstractTwitterCommand {

    static final String[] required = { "username" };

    protected String[] getRequired() {
        return required;
    }

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        OAuthConsumerHolder.logout();
        AccessKeyStore.removeAccessKey(args[0]);
        return Util.newList("Logged out successfully!");
    }

}
