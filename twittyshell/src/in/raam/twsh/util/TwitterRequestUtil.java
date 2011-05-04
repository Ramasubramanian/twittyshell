package in.raam.twsh.util;

import in.raam.twsh.comm.TwitterRequest;

import java.util.Map;
import java.util.Set;

/**
 * Utility class containing helper methods to operate on Twitter Request objects
 * @author raam
 *
 */
public abstract class TwitterRequestUtil {

    public static void validateRequest(Set<ParamInfo> info, TwitterRequest request) {
        Map<String, String> reqParams = request.params();
        for(ParamInfo pi : info) {
            if(pi.isRequired() 
                    && Util.isEmpty(reqParams.get(pi.key())))
                throw buildException(info);                
        }
    }
    
    private static ParamValidationException buildException(Set<ParamInfo> info) {
        StringBuilder sb = new StringBuilder("Invalid arguments, expected is : ");
        for(ParamInfo pi : info) {
            if(pi.isRequired())
                sb.append("<"+pi.key()+"> ");
            else
                sb.append("{"+pi.key()+"} ");
        }
        return new ParamValidationException(sb.toString());
    }
   
    
}
