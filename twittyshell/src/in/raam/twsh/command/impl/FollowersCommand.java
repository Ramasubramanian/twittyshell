package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.TWITTER_FOLLOWERS_URL;
import static in.raam.twsh.util.Constants.TWITTER_FOLLOWING_URL;
import in.raam.twsh.comm.TwitterRequest;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.util.List;

/**
 * Twitter command implementation to fetch the followers of the authenticated user
 * @author raam
 *
 */
public class FollowersCommand extends AbstractTwitterCommand {

    public static final FollowersCommand FOLLOWERS = new FollowersCommand(TWITTER_FOLLOWERS_URL);

    public static final FollowersCommand FOLLOWING = new FollowersCommand(TWITTER_FOLLOWING_URL);
    
    private String url;
    
    public FollowersCommand(String url) {
        this.url = url;
    }
  
    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        try {
            TwitterRequest tReq = new TwitterRequest(url);
            String resp = new JsonRestClient().getResponse(tReq,
                    OAuthConsumerHolder.getConsumer());
            return extractUsers(resp);
        } catch (Exception e) {
            TwittyShellExceptionHandler.handleException(e);
            return Util.newList("Error occured while fetching home timeline"); 
        }
    }

    
}
