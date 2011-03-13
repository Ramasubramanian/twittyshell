package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.TWITTER_UPDATE_URL;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Twitter command implementation to post a tweet in the autheticated user's account
 * @author raam
 *
 */
public class TweetCommand extends AbstractTwitterCommand {

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        Map<String,String>  paramMap = new HashMap<String, String>();
        String tweet = Util.mkString(args," ");
        if(tweet.length() > 140)
            return Util.newList("Sorry Twitter does not allow anything more than 140 characters! Go for blogging instead!!!");
        paramMap.put("status", Util.mkString(args," "));
        try {
            new JsonRestClient().postRequest(TWITTER_UPDATE_URL,
                    OAuthConsumerHolder.getConsumer(),paramMap);
            return Util.newList("Tweet posted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return Util.newList("Error occured while posting tweet"); 
        }
    }

}
