package in.raam.twsh.command;

import in.raam.twsh.command.impl.EchoingCommand;
import in.raam.twsh.command.impl.FollowersCommand;
import in.raam.twsh.command.impl.FriendshipCommand;
import in.raam.twsh.command.impl.LoginCommand;
import in.raam.twsh.command.impl.LogoutCommand;
import in.raam.twsh.command.impl.TimelineCommand;
import in.raam.twsh.command.impl.TweetCommand;
import in.raam.twsh.command.impl.TweetOpCommand;
import in.raam.twsh.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Static class that acts as a store for all Command objects created in the application. Command objects can be
 * fetched using the relevant string
 * @author raam
 *
 */
public final class CommandStore {

    private static Map<String, TwitterCommand> m_Map = new HashMap<String, TwitterCommand>();
    
    /**
     * Default command implementation for providing message to user when an invalid command is entered
     */
    private static  TwitterCommand INVALID_COMMAND  = new TwitterCommand(){

        @Override
        public List<String> execute(String[] args) {
            return Util.newList("Invalid command! List of valid commands : quit (or) "+m_Map.keySet());
        }

        @Override
        public boolean validateArgs(String[] args) {
            return true;
        }

        @Override
        public String getMessage(String command) {
            return "";
        }
        
    };
    
    static {
        m_Map.put("echo", new EchoingCommand());
        m_Map.put("login", new LoginCommand());
        m_Map.put("home", TimelineCommand.HOME_TIMELINE);
        m_Map.put("mentions", TimelineCommand.MENTIONS);
        m_Map.put("tweet", new TweetCommand());
        m_Map.put("logout", new LogoutCommand());
        m_Map.put("followers", FollowersCommand.FOLLOWERS);
        m_Map.put("following", FollowersCommand.FOLLOWING);
        m_Map.put("rtofme", TimelineCommand.RT_OF_ME);
        m_Map.put("retweeted", TimelineCommand.RETWEETED);
        m_Map.put("rm", TweetOpCommand.DESTROY_TWEET);
        m_Map.put("rt", TweetOpCommand.RETWEET);
        m_Map.put("follow", FriendshipCommand.FOLLOW_COMMAND);
        m_Map.put("dontfollow", FriendshipCommand.DONT_FOLLOW_COMMAND);
    }
    
    /**
     * Provides the command Object based on command String
     * @param str
     *              Command String entered by the user
     * @return
     */
    public static TwitterCommand get(String str) {
        TwitterCommand tc = m_Map.get(str); 
        return tc == null? INVALID_COMMAND:tc;
    }
    
}
