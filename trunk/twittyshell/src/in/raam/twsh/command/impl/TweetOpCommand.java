package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.*;
import in.raam.twsh.comm.DynamicUrlTwitterRequest;
import in.raam.twsh.comm.TwitterRequest;
import in.raam.twsh.command.domain.Tweet;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.util.List;

import com.google.gson.Gson;

/**
 * Command implementation to re-tweet or remove a tweet
 * @author raam
 *
 */
public class TweetOpCommand extends AbstractTwitterCommand {

    private String url;

    private String successMsg;

    private String failureMsg;

    public static final TweetOpCommand DESTROY_TWEET = new TweetOpCommand(
            TWEET_DESTROY_URL, "destroyed successfully",
            "Error occured while destroying tweet!");
    
    public static final TweetOpCommand RETWEET = new TweetOpCommand(
            RETWEET_URL, "retweeted successfully",
            "Error occured while retweeting!");
    
    

    @Override
    protected String[] getRequired() {
        return new String[] {"id"};
    }

    TweetOpCommand(String url, String successMsg, String failureMsg) {
        this.url = url;
        this.successMsg = successMsg;
        this.failureMsg = failureMsg;
    }

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        TwitterRequest tReq = new DynamicUrlTwitterRequest(url);
        tReq.addParam(args[0]);
        tReq.addParam("trim_user", "true");
        try {
            String resp = new JsonRestClient().postRequest(tReq,
                    OAuthConsumerHolder.getConsumer());
            Tweet t = extractTweet(resp);
            return Util.newList("Tweet \"" + t.getText() + "\" " + successMsg);
        } catch (Exception e) {
            TwittyShellExceptionHandler.handleException(e);
            return Util.newList(failureMsg);
        }
    }

    protected Tweet extractTweet(String jsonResponse) {
        Gson gson = new Gson();
        Tweet t = gson.fromJson(jsonResponse, Tweet.class);
        return t;
    }

}
