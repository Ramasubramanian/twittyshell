package in.raam.twsh.command.impl;

import static in.raam.twsh.util.Constants.DONT_FOLLOW_URL;
import static in.raam.twsh.util.Constants.FOLLOW_URL;
import static in.raam.twsh.util.Constants.LS;
import in.raam.twsh.comm.DynamicUrlTwitterRequest;
import in.raam.twsh.comm.TwitterRequest;
import in.raam.twsh.command.domain.User;
import in.raam.twsh.oauth.JsonRestClient;
import in.raam.twsh.oauth.OAuthConsumerHolder;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.io.FileNotFoundException;
import java.util.List;

import com.google.gson.Gson;

/**
 * Command implementation to follow/unfollow a friend
 * @author raam
 *
 */
public class FriendshipCommand extends AbstractTwitterCommand {
    
    public static final FriendshipCommand FOLLOW_COMMAND = new FriendshipCommand(FOLLOW_URL);
    
    public static final FriendshipCommand DONT_FOLLOW_COMMAND = new FriendshipCommand(DONT_FOLLOW_URL);
    
    private String url;
    
    public FriendshipCommand(String url) {
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        try {
            TwitterRequest tReq = new DynamicUrlTwitterRequest(url);
            tReq.addParam("id",args[0]);
            String resp = new JsonRestClient().postRequest(tReq,
                    OAuthConsumerHolder.getConsumer());
            User user = extractUser(resp);
            if(user == null)
                return Util.newList("User does not exist!");
            else
                return getSuccessMessage(user);
        } catch (FileNotFoundException e) {
            //if user is not available in twitter
            return Util.newList("User does not exist"); 
        } catch (Exception e) {
            TwittyShellExceptionHandler.handleException(e);
            return Util.newList("Error occured while trying to follow user"); 
        }
    }
    
    private List<String> getSuccessMessage(User user){
        if(this == FOLLOW_COMMAND)
            return Util.newList("Following user...."+LS+user);
        else
            return Util.newList("User...."+LS+user+" is not followed anymore!");
    }
    
    protected User extractUser(String jsonResponse) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonResponse, User.class);
        return user;
    }

}
