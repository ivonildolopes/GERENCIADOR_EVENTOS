package br.edu.fanor.progweb.arquitetura.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.aspectj.Loggable;
import br.edu.fanor.progweb.arquitetura.aspectj.PermitAll;
import br.edu.fanor.progweb.arquitetura.aspectj.RolesAllowed;
import br.edu.fanor.progweb.arquitetura.dao.UsuarioDAO;
import br.edu.fanor.progweb.arquitetura.entity.Usuarios;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;
import br.edu.fanor.progweb.arquitetura.utils.MessagesUtils;

/**
 * @author patrick.cunha
 * 
 */
@Loggable
@Component
public class UsuarioBO {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RolesAllowed(value = { "INCLUIR_USUARIO" })
	public void salvar(Usuarios usuario) {
		

			if(usuario.getLogin().length() >=4){
				usuario.setAtivo(true);
				usuarioDAO.salvar(usuario);
				MessagesUtils.info("Usuário salvo com sucesso!");	
			}else{
				MessagesUtils.error("Login deve conter no minino 4 caracteres");
				
			}
	}

	@RolesAllowed(value = { "ALTERAR_USUARIO" })
	public void atualizar(Usuarios usuario) {
		usuarioDAO.atualizar(usuario);
	}
		
	//metodo de logar
	@PermitAll
	@Loggable(enable = false)
	public Usuarios loggar(String login, String senha) {
		return usuarioDAO.buscarPorLoginSenha(login, senha);
	}

	@PermitAll
	@Loggable(enable = false)
	public Usuarios buscarUsuarioPorLogin(String login) {
		return usuarioDAO.buscarPorLogin(login);
	}

	@RolesAllowed(value = { "LISTAR_USUARIO" })
	@Loggable(enable = false)
	public List<Usuarios> listaUsuarioPorNome(String nome) {
		List<Usuarios> usuarios = usuarioDAO.listarPorNome(nome);
		return usuarios;
	}
	
	@RolesAllowed(value = { "LISTAR_USUARIO_TODOS" })
	@Loggable(enable = false)
	public List<Usuarios> listarTodos() {
		List<Usuarios> usuarios = usuarioDAO.listarTodos();
		return usuarios;
	}
	

	@PermitAll
	@Loggable(enable = false)
	public Usuarios buscarPorId(Integer id) {
		try {
			return usuarioDAO.buscaPorId(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RolesAllowed(value = { "EXCLUIR_USUARIO" })
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void excluir(Usuarios usuario) {
		try {
			usuario = usuarioDAO.buscaPorId(usuario.getId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioDAO.excluir(usuario);
	}

}
