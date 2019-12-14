package frassati.notabene.mailing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gets and parses needed users informations from the csv file.
 * @author Karlvallot
 */
public class ParsesUserInfos {
	
	List<User> users;
	
	/**
	 * Parses the csv file.
	 * @param filePath Path to the csv file.
	 * @throws IOException
	 */
	ParsesUserInfos(final String filePath) throws IOException {
		
		users = new ArrayList<User>();
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		
		// The first line ine the csv file is useless.
		br.readLine();
		
		String myLine = br.readLine();

		// Each line is a new user.
		while (myLine != null) {	
			
			String[] datas = myLine.split(";");
			
			if (!datas[0].isEmpty()
					&& !datas[2].isEmpty()
					&& !datas[3].isEmpty()
					&& !datas[6].isEmpty()) {
				
				User user = new User();
				user.setName(datas[0]);
				user.setId(datas[2]);
				user.setPassword(datas[3]);
				user.setEmail(datas[6]);
				
				users.add(user);
			}
			
		    myLine = br.readLine();
		}
		
		br.close();	
	}
	
	/**
	 * @return Users and datas in a list of java objects.
	 */
	public List<User> get() {	
		return this.users;	
	}
}
