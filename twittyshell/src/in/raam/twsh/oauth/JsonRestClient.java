package in.raam.twsh.oauth;

import in.raam.twsh.util.Util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import oauth.signpost.OAuthConsumer;

/**
 * Adapter class that acts as a JSON Rest client interacting with the twitter REST API server, internally takes care of 
 * signing OAuth requests with the signpost consumer
 * @author raam
 *
 */
public class JsonRestClient {    
    
    /**
     * Fire a HTTP request with provided url and return the JSON string response
     * @param urlStr
     *                  Rest URL to be accessed
     * @param consumer
     *                  OAuth Consumer implementation used to sign the requests 
     * @param params
     *                  Request parameters
     * @return
     * @throws Exception
     */
    public String getResponse(String urlStr, OAuthConsumer consumer,
            Map<String, ?> params) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        consumer.sign(request);
        request.connect(); 
        return Util.mkString(request.getInputStream());
    }

    /**
     * Assemble the request parameter String using the provided param map
     * @param params
     *                  Map object containing the request parameter as key-value pairs
     * @return
     * @throws Exception
     */
    public String getParamString(Map<String,?> params) throws Exception{
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, ?> e: params.entrySet())
            sb.append(e.getKey().toString()+"="+URLEncoder.encode(e.getValue().toString(),"UTF-8")).append("&");
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    /**
     * Post a request to the Twitter REST API server and return the response string as a JSON 
     * @param urlStr
     *                  Rest URL to be accessed
     * @param consumer
     *                  OAuth Consumer implementation used to sign the request 
     * @param params
     *                  Request param map
     * @return
     * @throws Exception
     */
    public String postRequest(String urlStr, OAuthConsumer consumer,
            Map<String, ?> params) throws Exception{
        String paramStr = getParamString(params);
        //System.out.println("Final URL::"+urlStr+"?"+paramStr);
        URL url = new URL(urlStr+"?"+paramStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");          
        consumer.sign(request);
        request.connect();
        return Util.mkString(request.getInputStream());        
    }
    
}
