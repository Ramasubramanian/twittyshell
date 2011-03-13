package in.raam.twsh.oauth;

/**
 * Wrapper object used to store the Access Key-Secret pair for a particular user
 * @author raam
 *
 */
public class AccessKeyWithSecret {

    private String key;
    
    private String secret;    
    
    AccessKeyWithSecret(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }
    
}
