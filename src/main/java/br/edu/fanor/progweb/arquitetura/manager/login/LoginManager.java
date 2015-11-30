/**
 * 
 */
package br.edu.fanor.progweb.arquitetura.manager.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.fanor.progweb.arquitetura.bussines.UsuarioBO;
import br.edu.fanor.progweb.arquitetura.entity.Usuarios;
import br.edu.fanor.progweb.arquitetura.to.SegurancaTO;
import br.edu.fanor.progweb.arquitetura.utils.Encripta;
import br.edu.fanor.progweb.arquitetura.utils.MessagesUtils;
import br.edu.fanor.progweb.arquitetura.utils.Navigation;

/**
 * @author IVONILDO LOPES
 * 
 */
@RequestScoped
@Component(value = "login")
@ManagedBean(name = "login")
public class LoginManager {

	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private SegurancaTO seguranca;
	
	private Usuarios usuario = new Usuarios();
	
	private boolean existsLogin;

	public String loggar() {
		// criptrogafar a senha					
		Usuarios usuario = this.usuarioBO.loggar(this.usuario.getLogin(),
				Encripta.encripta(this.usuario.getSenha()));
		
		this.usuario = new Usuarios();
		if (usuario != null) {
			seguranca.setUsuario(usuario);
			existsLogin = true;
			MessagesUtils.info("Bem vindo "+usuario.getNome());
			return Navigation.SUCESSO;
		} else {
			existsLogin = false;
			MessagesUtils.error("O login ou a senha inseridos estão incorretos.");
			return Navigation.FRACASSO;
		}
	}
	
	public String logout(){
		//usuario = null;
		existsLogin = false;
		return "listEvento?faces-redirect=true";
	}

	/**
	 * @return the usuario
	 */
	public Usuarios getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the existsEmail
	 */
	public boolean isExistsLogin() {
		return existsLogin;
	}

	/**
	 * @param existsEmail
	 *            the existsEmail to set
	 */
	public void setExistsLogin(boolean existsLogin) {
		this.existsLogin = existsLogin;
	}

}
