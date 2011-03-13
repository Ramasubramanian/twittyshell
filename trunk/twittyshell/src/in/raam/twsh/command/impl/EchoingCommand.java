package in.raam.twsh.command.impl;

import in.raam.twsh.command.TwitterCommand;
import in.raam.twsh.util.Util;

import java.util.List;

/**
 * Test command that just echoes the user input
 * @author raam
 *
 */
public class EchoingCommand implements TwitterCommand {

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#execute(java.lang.String[])
     */
    @Override
    public List<String> execute(String[] args) {
        return Util.newList(Util.mkString(args," "));
    }

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#getMessage(java.lang.String)
     */
    @Override
    public String getMessage(String command) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see in.raam.twsh.command.TwitterCommand#validateArgs(java.lang.String[])
     */
    @Override
    public boolean validateArgs(String[] args) {
        return false;
    }

}
