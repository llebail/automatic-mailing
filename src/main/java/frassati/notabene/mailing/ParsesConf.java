package frassati.notabene.mailing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;

/**
 * Gets and parses the configuration.
 * @author Karlvallot
 */
public class ParsesConf {
	
	JSONObject object;
	
	/**
	 * Parses the configuration file.
	 * @param filePath Path to the configuration file.
	 * @throws IOException
	 */
	ParsesConf(final String filePath) throws IOException {
		
		object = new JSONObject();	
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String myLine = br.readLine();

		while (myLine != null) {			
			String[] conf = myLine.split(";");			 
		    object.put(conf[0], conf[1]);
		    myLine = br.readLine();
		}
		
		br.close();	
	}
	
	/**
	 * @return The configuration in a JSONObject.
	 */
	public JSONObject get() {	
		return this.object;	
	}

}
