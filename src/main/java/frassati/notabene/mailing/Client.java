package frassati.notabene.mailing;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;

/**
 * Client connected to Gmail server.
 * @author Karlvallot
 */
public class Client {
	
    private static final String SMTP_SERVER = "SMTP_SERVER";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";  
    private static final String PORT = "PORT";
    
    private static String EMAIL_FROM;

	Session session;

	/**
	 * Constructor.
	 * @param object
	 */
	Client(final JSONObject object) {
		
		EMAIL_FROM = (String) object.get(USERNAME);
		
		final String username = (String) object.get(USERNAME);
		final String password = (String) object.get(PASSWORD);

    	Properties prop = new Properties();
    	prop.put("mail.smtp.host", object.get(SMTP_SERVER));
        prop.put("mail.smtp.port", object.get(PORT));
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", object.get(PORT));
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });	
	}
	
	/**
	 * Send an email to each user in the list.
	 * @param users List of users with their info.
	 */
    public void sendMails(final List<User> users) {
    	
    	for (User user : users) {
    		
	        // The password has never been changed.
	        if (!user.getPassword().startsWith("**") && !user.getPassword().endsWith("**")) {
	        	
		        try {
		
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress(EMAIL_FROM));
		            message.setRecipients(
		                    Message.RecipientType.TO,
		                    InternetAddress.parse(user.getEmail())
		            );
		            message.setSubject("Consultation des notes");
		
		            // Message to be sent.
		            //TODO Use another way...
		            message.setText("Bonjour Monsieur, Madame " + user.getName() + ","
		            		+ "\n"
		                    + "\nVoici vos identifiants pour le site de notes de Frassati la vie scolaire."
		            		+ "\n"
		            		+ "\nPour vous connecter : https://collegebxfrassati88.la-vie-scolaire.fr/login"
		            		+ "\n"
		                    + "\nIdentifiant : " + user.getId()
			                + "\nMot de passe : " + user.getPassword()
			                + "\n"
			                + "\nCordialement,"
			                + "\n"
			                + "\nKarl VALLOT"
			                + "\nInformaticien officieux du Collège & Lycée  Bx Frassati"
			                + "\n"
			                + "\nCeci est un mail automatique, merci de na pas répondre."
			                + "\nPour toute question ou problème, veuillez contacter karlvallot@hotmail.com");
		            
		            Transport.send(message);
		
		            System.out.println(String.format("Mail envoyé à : %s", user.getEmail()));
		            
		        } catch (MessagingException e) {
		            e.printStackTrace();		      
		        }    
		    }
    	}
    }
}
