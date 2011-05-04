package in.raam.twsh.comm;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Object representation of a request to be sent to Twitter REST API
 * @author raam
 *
 */
public class TwitterRequest {

    protected final String url;
    
    protected final Map<String,String> m_Params = new HashMap<String, String>();
    
    public TwitterRequest(String url) {
        this.url = url;
    }
    
    public String url() {
        return url;
    }
    
    public void addParam(String key,String value) {
        m_Params.put(key, value);
    }
    
    public void addParam(String key,Object value) {
        m_Params.put(key, value==null?"":value.toString());
    }
    
    public Map<String,String> params(){
        return m_Params;
    }
    
    public void addParams(String[] keyValues) {
        for(String keyValue : keyValues)
            addParam(keyValue);
    }
    
    /**
     * Helper method to parse key=value type strings and the request param
     * accordingly
     * @param keyValue
     */
    public void addParam(String keyValue) {
        if(keyValue!=null) {
            if(keyValue.contains("=")) {
                String[] arr = keyValue.split("=");
                m_Params.put(arr[0], arr.length>1?arr[1]:"");
            }else
                m_Params.put(keyValue, "");
        }
    }
    
    /**
     * Assemble the request parameter String using the provided param map
     * @param params
     *                  Map object containing the request parameter as key-value pairs
     * @return
     * @throws Exception
     */
    public String paramString() throws Exception{
        if(m_Params.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder("?");        
        for(Map.Entry<String, ?> e: m_Params.entrySet())
            sb.append(e.getKey().toString()+"="+URLEncoder.encode(e.getValue().toString(),"UTF-8")).append("&");
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
        
}
