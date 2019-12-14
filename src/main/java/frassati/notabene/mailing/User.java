package frassati.notabene.mailing;

/**
 * A user and his informations.
 * @author Karlvallot
 */
public class User {
	
	private String name;
	private String id;
	private String password;
	private String email;
	
	/**
	 * Empty constructor.
	 */
	User() {
		
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public void setId(final String id) {
		this.id = id;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
}
