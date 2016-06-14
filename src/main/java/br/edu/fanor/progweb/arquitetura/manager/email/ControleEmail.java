package br.edu.fanor.progweb.arquitetura.manager.email;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.edu.fanor.progweb.arquitetura.utils.EmailUtil;
import br.edu.fanor.progweb.arquitetura.utils.MessageEmail;

@ManagedBean(name = "controleEmail")
@RequestScoped
public class ControleEmail {

	MessageEmail mensagem = new MessageEmail();

	public void enviaEmail() {
		try {
			EmailUtil.enviaEmail(mensagem);
		} catch (EmailException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro! Occoreu um erro ao enviar a mensagem.",
							"Erro"));
			// Logger.getLogger(ControleEmail.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}

	public void limpaCampos() {
		mensagem = new MessageEmail();
	}

	public MessageEmail getMensagem() {
		return mensagem;
	}

	public void setMensagem(MessageEmail mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
