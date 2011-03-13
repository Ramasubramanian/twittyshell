package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.*;

import in.raam.twsh.command.domain.User;
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
            String resp = new JsonRestClient().getResponse(url,
                    OAuthConsumerHolder.getConsumer(),
                    new HashMap<String, String>());
            Gson gson = new Gson();
            Type collType = new TypeToken<Collection<User>>() {}.getType();
            Collection<User> users = gson.fromJson(resp, collType);
            List<String> l = new ArrayList<String>();
            for(User u: users)
                l.add(u.toString());
            return l;            
        } catch (Exception e) {
            e.printStackTrace();
            return Util.newList("Error occured while fetching home timeline"); 
        }
    }

}
