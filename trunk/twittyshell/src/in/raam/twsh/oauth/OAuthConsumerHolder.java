package in.raam.twsh.oauth;

import in.raam.twsh.main.TwittyShell;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

import static in.raam.twsh.util.Constants.*;

/**
 * Static holder class which acts as custodian for OAuth consumer instance used within the application, when a user login freshly
 * a new instance of the consumer is created and held by this class
 * @author raam
 *
 */
public final class OAuthConsumerHolder {

    private static boolean authorized;

    private OAuthConsumerHolder() {/* static usage */}

    private static OAuthConsumer mConsumer;

    private static OAuthProvider mProvider;

    /**
     * Returns the OAuth consumer instance held by this class
     * @return
     */
    public static OAuthConsumer getConsumer() {
        if (!authorized)
            return null;
        return mConsumer;
    }

    /**
     * Authorize a user using OAuth or from the keys already available in the key store
     * @param userName
     *                  Name of the user to be authorized
     * @throws Exception
     */
    public static void authorize(String userName) throws Exception {
        if (authorized)
            throw new AlreadyAuthorizedException(
                    "Already authorized, please use logout and try again!");
        mConsumer = new DefaultOAuthConsumer("kjgGFFJjjhsjiweupwpdmFgFssg",
                "GhjgsHGGplyertavmxmskdhawq");

        AccessKeyWithSecret aws = AccessKeyStore
                .getAccessKeyWithSecret(userName);
        if (aws == null) {
            authorizeNewUser(userName);
        } else {
            mConsumer.setTokenWithSecret(aws.getKey(), aws.getSecret());
        }
        authorized = true;
    }

    /**
     * Authorize a new user by requesting a new access token and PIN number from the Twitter server
     * @param userName
     * @throws Exception
     */
    public static void authorizeNewUser(String userName) throws Exception{
        mProvider = new DefaultOAuthProvider(TWITTER_REQUEST_TOKEN_URL,
                TWITTER_ACCESS_TOKEN_URL, TWITTER_AUTHOROIZE_URL);

        String authUrl = mProvider.retrieveRequestToken(mConsumer,
                OAuth.OUT_OF_BAND);

        System.out
                .println("Please visit:\n"
                        + authUrl
                        + "\n... in your browser and grant TwittyShell authorization");
        System.out
                .println("Enter the PIN code and hit ENTER when you're ready to type commands for twitter!");
        String pin = TwittyShell.readLine();
        mProvider.retrieveAccessToken(mConsumer, pin);
        AccessKeyStore.addAccessKey(userName, mConsumer.getToken(), mConsumer.getTokenSecret());
    }
    
    /**
     * Log out the user from system
     */
    public static void logout() {
        authorized = false;
        mProvider = null;
        mConsumer = null;
    }
}
