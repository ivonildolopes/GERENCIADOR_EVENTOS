package br.edu.fanor.progweb.arquitetura.manager.email;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import br.edu.fanor.progweb.arquitetura.utils.Email;

@ManagedBean(name="emailManager")
@SessionScoped
@Component(value="emailManager")
public class EmailManager {
	
	private Email email = new Email();
	
	private String destinatario=null;
	private String titulo=null;
	private String corpo=null;
//	private String emaill;
//	private String senha;
	
	
	public void enviarEmail(){
		email.loginEmail("ivonildolopes@gmail.com","010203PJ");
		
		email.setDestinatario(destinatario);
		email.setTitulo(titulo);
		email.setCorpo(corpo);
		
		
	}
	
	public void limpaCampos(){
		destinatario = "";
		titulo = "";
		corpo = "";
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

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

//	public String getEmaill() {
//		return emaill;
//	}
//
//	public void setEmaill(String emaill) {
//		this.emaill = emaill;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
	
	

}
