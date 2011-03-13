package in.raam.twsh.command.domain;

import static in.raam.twsh.util.Constants.*;

/**
 * Domain object representing a Tweet
 * @author raam
 *
 */
public class Tweet {

    private String text;

    private Long id;
    
    private String created_at;    
    
    private User user;
        
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String createdAt) {
        created_at = createdAt;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(text+LS);
        sb.append("["+user.getName()+"] ");
        sb.append("["+user.getScreen_name()+"] ");
        sb.append("["+id+"] ");
        sb.append("["+created_at+"]"+LS+"--------------------------------");
        return sb.toString();
    }
    
}
