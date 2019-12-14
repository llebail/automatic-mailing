package frassati.notabene.mailing;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

/**
 * Sends mails to la-vie-scolaire users with their id and password.
 * @author Karlvallot
 */
public class Launcher {
	
	/*
	 * Path to the configuration file.
	 */
	private static final String CONF_FILE_PATH = "resources/conf.txt";
	
	/*
	 * Path to the users data file.
	 */
	private static final String DATAS_FILE_PATH = "resources/users.csv";
    
    /**
     * Launches the application.
     * @param args
     * @throws IOException
     */
	public static void main(String[] args) throws IOException {
    	
    	// Gets and parses the configuration.
    	JSONObject conf = new ParsesConf(CONF_FILE_PATH).get();
    	
    	// Creates the client and connects with Gmail server.
    	Client client = new Client(conf);
    	
    	// Gets and parses needed users informations from the csv file.
    	List<User> users = new ParsesUserInfos(DATAS_FILE_PATH).get();
    	
    	// Sends mails.
    	client.sendMails(users);
    }
}
