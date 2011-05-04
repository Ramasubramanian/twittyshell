package in.raam.twsh.comm;

import in.raam.twsh.util.ParamValidationException;
import in.raam.twsh.util.Util;

/**
 * Twitter request to wrap a dynamic URL, parameters enclosed between curly braces are replaced with 
 * corresponding request parameter values e.g. http://aaa.com/bbb/${id}.json will be replaced with
 * the value of http://aaa.com/bbb/raam.json where id=raam should be added as a request parameter
 * @author raam
 *
 */
public class DynamicUrlTwitterRequest extends TwitterRequest {

    private static final String BEGIN = "${";
    
    private static final String END = "}";
    
    public DynamicUrlTwitterRequest(String url) {
        super(url);
    }

    @Override
    public String url() {        
        return transformUrl(url);
    }
    
    private String transformUrl(String url) {
        StringBuilder sb = new StringBuilder(url);
        int startIdx = sb.indexOf(BEGIN);
        int endIdx = sb.indexOf(END);
        String key,value;
        while(startIdx >= 0) {
            key = sb.substring(startIdx+2, endIdx);
            value = m_Params.get(key);
            if(Util.isEmpty(value))
                throw new ParamValidationException("Invalid arguments : expecting <"+key+">");
            else {
                sb.replace(startIdx, endIdx+1, value);
                m_Params.remove(key); //since it is substituted in the URL no need to pass as request param later on
            }
            startIdx = sb.indexOf(BEGIN);
            endIdx = sb.indexOf(END);
        }
        return sb.toString();

    }
    
}
