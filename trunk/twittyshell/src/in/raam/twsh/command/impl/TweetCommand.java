package in.raam.twsh.command.impl;


import static in.raam.twsh.util.Constants.TWITTER_UPDATE_URL;
import in.raam.twsh.comm.TwitterRequest;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.ParamInfo;
import in.raam.twsh.util.TwitterRequestUtil;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.util.List;
import java.util.Set;

/**
 * Twitter command implementation to post a tweet in the authenticated user's account
 * @author raam
 *
 */
public class TweetCommand extends AbstractTwitterCommand {

    private static final Set<ParamInfo> paramInfo = Util.newSet(ParamInfo.mandatory("status"),
            ParamInfo.optional("include_entities"));
    
        
    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    public List<String> execute(String[] args) {
        String tweet = Util.mkString(args," ");
        try {        
            if(tweet.length() > 140)
                return Util.newList("Sorry Twitter does not allow anything more than 140 characters! Go for blogging instead!!!");
            TwitterRequest tReq = new TwitterRequest(TWITTER_UPDATE_URL);
            tReq.addParam("status", tweet);
            tReq.addParam("include_entities", "true");
            TwitterRequestUtil.validateRequest(paramInfo,tReq);
            new JsonRestClient().postRequest(tReq,OAuthConsumerHolder.getConsumer());
            return Util.newList("Tweet posted successfully!");
        } catch (Exception e) {
            TwittyShellExceptionHandler.handleException(e);
            return Util.newList("Error occured while posting tweet"); 
        }
    }

}
