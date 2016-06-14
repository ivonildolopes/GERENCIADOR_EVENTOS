package br.edu.fanor.progweb.arquitetura.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Email {

	private String destinatario=null;
	private String titulo=null;
	private String corpo=null;

	public void loginEmail(final String email, final String senha) {
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
		
		
		 Session session = Session.getDefaultInstance(props,
                 new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication()
                      {
                       //o email e a senha de quem vai mandar
                            return new PasswordAuthentication(email, senha);
                      }
                 });

//		Session sessao = Session.getDefaultInstance(props, new Authenticator() {
//			@SuppressWarnings("unused")
//			protected PasswordAuthentication getAuthentication() {
//				// email e senha de quem vai mandar
//				return new PasswordAuthentication(email, senha);
//			}
//		});

		 
		 
		// ativa Debug para sessão

		 /** Ativa Debug para sessão */
	        session.setDebug(true);
	 
	        try {
	 
	              Message message = new MimeMessage(session);
	              message.setFrom(new InternetAddress("ivonildolopes@gmail.com")); //Remetente
	 
	              Address[] toUser = InternetAddress.parse(destinatario); 
	 
	              message.setRecipients(Message.RecipientType.TO, toUser);
	              message.setSubject(titulo);//Assunto
	              message.setText(corpo);
	              /**Método para enviar a mensagem criada*/
	              Transport.send(message);
	 
	              System.out.println("Feito!!!");
	 
	         } catch (MessagingException e) {
	          System.out.println("errado "+ e);
	              throw new RuntimeException(e);
	              
	        }
	  
	 
	 }
		 
//		sessao.setDebug(true);
//
//		try {
//			Message mensagem = new MimeMessage(sessao);
//
//			// remetente
//			mensagem.setFrom(new InternetAddress("ivonildolopes@gmail.com"));
//
//			// destinatario
//			Address[] toUser = InternetAddress.parse(destinatario);
//
//			mensagem.setRecipients(Message.RecipientType.TO, toUser);
//
//			// titulo
//			mensagem.setSubject(titulo);
//
//			// corpo
//			mensagem.setText(corpo);
//
//			// enviar mensagem
//			Transport.send(mensagem);
//
//			System.out.println("email enviado com sucesso");
//			// MessagesUtils.info("Email Enviado com sucesso");
//		} catch (MessagingException e) {
//			System.out.println("erro " + e);
//			MessagesUtils.error("Erro ao enviar email");
//			throw new RuntimeException(e);
//
//		}
//
//	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

}
