package in.raam.twsh.oauth;

/**
 * Utility class to encrypt and decrypt the user names to be stored based on a 
 * small algorithm
 * @author raam
 *
 */
public class CryptDecrypter {

    private static final int DELTA = 4;
    
    public static String encrypt(String input) {
        StringBuilder  s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        boolean flag = true;
        for(char s:input.toCharArray()) {
            if(flag)
                s1.append(add(s,DELTA));
            else
                s2.append(add(s,-DELTA));            
            flag = !flag;
        }
        return s1.append(s2).toString();
    }
    
    private static char add(char c,int num) {
        return (char)(c + num);
    }
    
    public static String decrypt(String input) {
        StringBuilder ret = new StringBuilder();
        int len = input.length();
        int mid = len % 2 == 0 ? (len/2) - 1 : (len/2);
        char[] arr = input.toCharArray();
        for(int i=0,j=mid+1;i<=mid;i++,j++) {
            ret.append(add(arr[i],-DELTA));
            if(j<len) ret.append(add(arr[j],DELTA));
        }
        return ret.toString();
    }      
    
}

