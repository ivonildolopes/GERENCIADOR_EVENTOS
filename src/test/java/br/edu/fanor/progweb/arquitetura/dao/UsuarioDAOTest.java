package br.edu.fanor.progweb.arquitetura.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.fanor.progweb.arquitetura.entity.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class UsuarioDAOTest {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Test
	@Ignore
	public void testSalvar() throws Exception {
		
		Usuarios usuario = new Usuarios();
		usuario.setNome("adriano");
		usuario.setSenha("123456");
		usuario.setLogin("adriano@gmail.com");
//		usuario.setPrimeiroAcesso(true);
		usuario.setAtivo(false);
		usuarioDAO.salvar(usuario);
		
		Assert.assertNotNull(usuario.getId());
		System.out.println(usuario.getId());
		
	}
	
	@Test
	@Ignore
	public void testListaPorNome(){
		List<Usuarios> usuarios = usuarioDAO.listarPorNome("IVO");
		Assert.assertEquals(1, usuarios.size());
	}

	
}
