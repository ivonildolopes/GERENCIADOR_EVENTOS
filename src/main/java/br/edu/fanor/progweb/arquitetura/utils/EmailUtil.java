package br.edu.fanor.progweb.arquitetura.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String USERNAME = "ivonildolopes@gmail.com";
	private static final String PASSWORD = "010203PJ";
	private static final String EMAILORIGEM = "ivonildolopes@gmail.com";

	@SuppressWarnings("deprecation")
	public static org.apache.commons.mail.Email conectaEmail()
			throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setTLS(true);
		
		email.setFrom(EMAILORIGEM);
		return email;
	}

	public static void enviaEmail(MessageEmail mensagem) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email = (SimpleEmail) conectaEmail();
		email.setSubject(mensagem.getTitulo());
		email.setMsg(mensagem.getMensagem());
		email.addTo(mensagem.getDestino());
		String resposta = email.send();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"E-mail enviado com sucesso para: "
								+ mensagem.getDestino(), "Informação"));
	}
}