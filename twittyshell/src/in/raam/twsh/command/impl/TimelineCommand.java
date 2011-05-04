package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.HOME_TIMELINE_URL;
import static in.raam.twsh.util.Constants.TWITTER_MENTIONS_URL;
import static in.raam.twsh.util.Constants.TWITTER_RT_BY_ME_URL;
import static in.raam.twsh.util.Constants.TWITTER_RT_OF_ME_URL;
import in.raam.twsh.comm.TwitterRequest;
import in.raam.twsh.command.domain.Tweet;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.ParamInfo;
import in.raam.twsh.util.TwitterRequestUtil;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Twitter command implementation to fetch the timelines, retweets and mentions of the authenticated 
 * user
 * @author raam
 *
 */
public class TimelineCommand extends AbstractTwitterCommand {
    
    private static final Set<ParamInfo> paramInfo = Util.newSet(ParamInfo.optional("page"),
            ParamInfo.optional("count"));
    
    public static final TimelineCommand HOME_TIMELINE = new TimelineCommand(HOME_TIMELINE_URL);

    public static final TimelineCommand MENTIONS = new TimelineCommand(TWITTER_MENTIONS_URL);
    
    public static final TimelineCommand RT_OF_ME = new TimelineCommand(TWITTER_RT_OF_ME_URL);
    
    public static final TimelineCommand RETWEETED = new TimelineCommand(TWITTER_RT_BY_ME_URL);
    
        
    private String url;
    
    public TimelineCommand(String url) {
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
            tReq.addParams(args);
            TwitterRequestUtil.validateRequest(paramInfo, tReq);
            String resp = new JsonRestClient().getResponse(tReq,
                    OAuthConsumerHolder.getConsumer());
            return extractTweets(resp); 
        } catch (Exception e) {
            TwittyShellExceptionHandler.handleException(e);
            return Util.newList("Error occured while fetching home timeline"); 
        }
    }
    
    protected List<String> extractTweets(String jsonResponse){
        Gson gson = new Gson();
        Type collType = new TypeToken<Collection<Tweet>>() {}.getType();
        Collection<Tweet> tweets  = gson.fromJson(jsonResponse, collType);
        List<String> l = new ArrayList<String>();
        for(Tweet t: tweets)
            l.add(t.toString());
        return l;       
    }

}
