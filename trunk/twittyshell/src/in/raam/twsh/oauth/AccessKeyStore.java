package in.raam.twsh.oauth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import static in.raam.twsh.util.Constants.*;

/**
 * Helper class that acts as an in-memory store for storing access keys and secrets. Tokens added
 * to this class will be serialized into the key store file located in user.home folder and used
 * in all future transactions
 * @author raam
 *
 */
public class AccessKeyStore {

    private static Map<String, AccessKeyWithSecret> m_Keys = new HashMap<String, AccessKeyWithSecret>();

    static {
        loadKeys();
    }

    private static void loadKeys() {
        try {
            File f = getKeyStoreFile();
            String line;
            if(f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                while((line = br.readLine())!=null)
                    m_Keys.put(CryptDecrypter.decrypt(line.split("=")[0]), getObject(line.split("=")[1]));
                br.close();
            }else {
                System.out.println("Keystore not found, suppose this is your first login in this machine");
            }
        } catch (Exception e) {
            System.out.println("Keystore not found, suppose this is your first login in this machine");
        }
    }

    private static AccessKeyWithSecret getObject(String string) {
        String[] arr = string.split(",");
        return new AccessKeyWithSecret(arr[0], arr[1]);
    }

    /**
     * Get the access key and secret corresponding to the username
     * @param userName
     * @return
     */
    public static AccessKeyWithSecret getAccessKeyWithSecret(String userName) {
        return m_Keys.get(userName);
    }
    
    /**
     * Add a new access key-secret pair for the specified user
     * @param userName
     * @param key
     * @param secret
     */
    public static void addAccessKey(String userName,String key,String secret) {
        m_Keys.put(userName,new AccessKeyWithSecret(key, secret));       
    }
    
    /**
     * Remove the access key-secret pair for the specified user from store 
     * @param userName
     */
    public static void removeAccessKey(String userName) {
        m_Keys.remove(userName);
    }
    
    private static File getKeyStoreFile() {
        String file = System.getProperty("user.home")+ FS +".twittyshell";
        return new File(file);
    }
    
    
    public static void saveKeyStore() {
        FileWriter fw ;
        try {
            fw = new FileWriter(getKeyStoreFile());
            for(Map.Entry<String, AccessKeyWithSecret> e: m_Keys.entrySet()) {
                fw.write(CryptDecrypter.encrypt(e.getKey())+"="+e.getValue().getKey()+","+e.getValue().getSecret()+LS);
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error happened while saving keystore, you may need to authorize again!");
        }
        
    }
}
