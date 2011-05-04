package in.raam.twsh.util;

/**
 * Class to hold meta data information about a Twitter Request parameter 
 * @author raam
 *
 */
public class ParamInfo {

    private String key;
    
    private boolean required;       
    
    public static ParamInfo mandatory(String key) {
        return new ParamInfo(key,true);
    }    

    public static ParamInfo optional(String key) {
        return new ParamInfo(key,false);
    }
    
    private ParamInfo(String key, boolean required) {
        this.key = key;
        this.required = required;
    }

    public String key() {
        return key;
    }

    public boolean isRequired() {
        return required;
    }
    
}
