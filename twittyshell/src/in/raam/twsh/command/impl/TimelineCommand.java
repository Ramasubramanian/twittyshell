package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.*;
import in.raam.twsh.command.domain.Tweet;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.Util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Twitter command implementation to fetch the timelines, retweets and mentions of the autheticated user
 * @author raam
 *
 */
public class TimelineCommand extends AbstractTwitterCommand {
    
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
            String resp = new JsonRestClient().getResponse(url,
                    OAuthConsumerHolder.getConsumer(),
                    new HashMap<String, String>());
            Gson gson = new Gson();
            Type collType = new TypeToken<Collection<Tweet>>() {}.getType();
            Collection<Tweet> tweets  = gson.fromJson(resp, collType);
            List<String> l = new ArrayList<String>();
            for(Tweet t: tweets)
                l.add(t.toString());
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return Util.newList("Error occured while fetching home timeline"); 
        }
    }

}
