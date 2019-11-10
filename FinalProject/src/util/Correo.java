package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {

	public static void main(String[] args) {

		String destinatario = "cgsanchezp@uqvirtual.edu.co";
		String asunto = "texto";
		String cuerpo = "https://docs.google.com/forms/d/e/1FAIpQLScNMt2zkr6j1cJTkREpSlLAELN9LrGXr72ro8V3XH90WjUXLQ/viewform?usp=sf_link";
		enviarConGMail(destinatario, asunto, cuerpo);

	}

	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "analisisdealgoritmos12@gmail.com"; // Para la dirección nombrecuenta@gmail.com

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "sergiotoc2"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			// Se podrían añadir varios de la misma manera
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "sergiotoc2");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("mensaje enviado");
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

}
