package in.raam.twsh.command.domain;

/**
 * Domain object representing a User
 * @author raam
 *
 */
public class User {

    private String name;

    private String screen_name;

    private String created_at;
    
    private String followers_count;
    
    private String description;
    
    private String statuses_count;
    
    private String friends_count;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(String statusesCount) {
        statuses_count = statusesCount;
    }

    public String getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(String friendsCount) {
        friends_count = friendsCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;
    
    public String getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(String followersCount) {
        followers_count = followersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screenName) {
        screen_name = screenName;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String createdAt) {
        created_at = createdAt;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("["+name+"] ");
        sb.append("["+screen_name+"] ");
        sb.append("[Followers: "+followers_count+"] ");        
        sb.append("[Following: "+friends_count+"] \n");
        sb.append("[Tweets: "+statuses_count+"] ");
        sb.append("["+created_at+"] ");
        sb.append("[Location: "+location+"] \n");
        sb.append("["+description+"]\n--------------------------------");
        return sb.toString();
    }    
    
}
