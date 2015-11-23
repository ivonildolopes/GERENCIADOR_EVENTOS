package br.edu.fanor.progweb.arquitetura.manager.usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.fanor.progweb.arquitetura.bussines.UsuarioBO;
import br.edu.fanor.progweb.arquitetura.entity.Usuarios;
import br.edu.fanor.progweb.arquitetura.utils.Encripta;
import br.edu.fanor.progweb.arquitetura.utils.MessagesUtils;
import br.edu.fanor.progweb.arquitetura.utils.Navigation;

/**
 * @author patrick.cunha
 * 
 */
@RequestScoped
@ManagedBean(name = "cadUsuario")
@Component(value = "cadUsuario")
public class CadUsuarioManager {

	@Autowired
	private UsuarioBO usuarioBO;
	@Autowired
	private ListUsuarioManager listUsuario;
	private String nome;
	private String login;
	private String senha;
	private Boolean ativo;

	public String salvar() {
		Usuarios usuario = new Usuarios();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setAtivo(ativo);
		usuario.setSenha(Encripta.encripta(senha));

		usuarioBO.salvar(usuario);
		MessagesUtils.info("Usuário salvo com sucesso!");
		listUsuario.lista();

		return Navigation.SUCESSO;
	}

	public String preparaSalvar() {
		this.limpaDados();

		return Navigation.SUCESSO;
	}

	public void limpaDados() {
		this.nome = "";
		this.login = "";
		this.senha = "";
		this.ativo = false;
	}

	// GET AND SET
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
