package com.analyzer.analyzer.email;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveEmail {

	public static void fetchClient(String from) throws ClassNotFoundException {
		String[] infosClient = from.split(" ");
		System.out.println("nom:" + infosClient[0] + "prenom:" + infosClient[1]);

		Class.forName("org.h2.Driver");
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:./mailstone;AUTO_SERVER=TRUE", "", "");
			String query = "INSERT INTO Client(Nom, Prenom, Email)VALUES(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, infosClient[0]);
			pstmt.setString(2, infosClient[1]);
			pstmt.setString(3, infosClient[2]);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	public static void fetchIssue(String content) throws ClassNotFoundException {
		String[] References = { "lg", "brandt", "marque3", "marque4" };
		String[] infosProduits = content.split(" ");
		System.out.println("ref:" + infosProduits[0] + "brand:" + infosProduits[1]);
		Class.forName("org.h2.Driver");
		try {
			for (int i = 0; i < 4; i++) {

				if (content.contains(References[i])) {
					Connection conn = DriverManager.getConnection("jdbc:h2:./mailstone;AUTO_SERVER=TRUE", "", "");
					String query = "INSERT INTO Product(ref, brand)VALUES(?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, References[i]);
					pstmt.setString(2, References[i]);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM Client");
					System.out.println(rs);

				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	public static void receiveEmail(String pop3Host,
			String storeType, String user, String password) {
		Properties props = new Properties();
		props.put("mail.pop3.host", pop3Host);
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.starttls.enable", "true");
		props.put("mail.store.protocol", "pop3");

		Session session = Session.getInstance(props);
		try {
			Store mailStore = session.getStore(storeType);
			mailStore.connect(pop3Host, user, password);

			Folder folder = mailStore.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			Message[] emailMessages = folder.getMessages();
			System.out.println("Total Message - "
					+ emailMessages.length);

			// Iterate the messages
			for (int i = 0; i < emailMessages.length; i++) {
				Message message = emailMessages[i];
				Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
				System.out.println();
				System.out.println("Email " + (i + 1) + "-");
				System.out.println("Subject - " + message.getSubject());
				System.out.println("From - " + message.getFrom()[0]);
				fetchClient(message.getFrom()[0].toString());

				System.out.println("To - ");
				for (int j = 0; j < toAddress.length; j++) {
					System.out.println(toAddress[j].toString());
				}
				System.out.println("Message : ");

				Multipart multipart = (Multipart) emailMessages[i].getContent();

				for (int x = 0; x < multipart.getCount() - 1; x++) {
					BodyPart bodyPart = multipart.getBodyPart(x);

					String disposition = bodyPart.getDisposition();

					if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
						System.out.println("le mail contient une pièce jointe : ");

						DataHandler handler = bodyPart.getDataHandler();
						System.out.println("nom du fichier : " + handler.getName());
					} else {
						fetchIssue(bodyPart.getContent().toString());
						System.out.println(bodyPart.getContent());
					}
				}
			}

			folder.close(false);
			mailStore.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error lors de la réception du mail.");
		}
	}

}