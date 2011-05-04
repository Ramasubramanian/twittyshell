package in.raam.twsh.main;

import in.raam.twsh.command.CommandStore;
import in.raam.twsh.command.TwitterCommand;
import in.raam.twsh.oauth.AccessKeyStore;
import in.raam.twsh.util.TwittyShellExceptionHandler;
import in.raam.twsh.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main launcher of the Twitter command line client
 * @author raam
 *
 */
public final class TwittyShell {

    public static final String DEFAULT_PROMPT = "twittyshell> ";
    
    public static String prompt = DEFAULT_PROMPT; 
    
    public static final String QUITSIG = "quit";
    
    public static final String SPACE_R = "[\\s]+";
    
    public static void addUser(String userId) {
        prompt = userId + "@" + DEFAULT_PROMPT;
    }
    
    public static void removeUser() {
        prompt = DEFAULT_PROMPT;
    }
    
    public static void disp(String str) {
        System.out.print(prompt + str);
    }
    
    public static void dispLine(String str) {
        System.out.println(prompt + str);
    }
    
    
    
    /**
     * Reads a line from the console and send it to the caller
     * @return
     */
    public static String readLine(){
        disp("");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input= br.readLine();
        } catch (IOException e) {
            TwittyShellExceptionHandler.handleException(e);
            return "";
        }
        return input;
    }
    
    /**
     * Process the input command from the console
     * @param str
     *              Command String entered from the console
     * @return
     */
    public static List<String> processInput(String str) {
        String[] arr = str.split(SPACE_R);
        TwitterCommand tc = CommandStore.get(arr[0]);
        String[] args = Util.slice(1, arr.length, arr);
        if(tc.validateArgs(args))            
            return tc.execute(args);
        else
            return Util.newList(tc.getMessage(arr[0]));
    }
    
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        dispLine("Welcome to "+AppInfo.getName()+" "+AppInfo.getVersion()+"!");
        String input;
        while(!(input=readLine()).equals(QUITSIG)) {
            if(Util.isEmpty(input)) {
                dispLine("Invalid Command!");
                continue;
            }
            try {
                System.out.println(Util.mkString(processInput(input),"\n"));
            }catch(Exception e) {
                TwittyShellExceptionHandler.handleException(e);
            }
        }
        AccessKeyStore.saveKeyStore();
    }

}
