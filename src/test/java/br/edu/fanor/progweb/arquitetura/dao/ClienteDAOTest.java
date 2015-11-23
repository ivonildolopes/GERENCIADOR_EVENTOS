package br.edu.fanor.progweb.arquitetura.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.entity.Clientes;

@RunWith(SpringJUnit4ClassRunner.class) // essa classe te teste precisa do contexto do spring
@ContextConfiguration("/test-context.xml")
@TransactionConfiguration(defaultRollback=false) // para nunca da roolback
@Transactional(propagation=Propagation.REQUIRED)
public class ClienteDAOTest {

	@Autowired // essa assinatura enjeta um objeto dentrp desse atributo
	private ClienteDAO clienteDAO;
	
	@Test
	public void testSalvar() {
		//fail("Not yet implemented");
		Clientes cliente = new Clientes();
		
		cliente.setNome("Kassia Ramos");
		cliente.setCpf("054.598.133-60");
		
		clienteDAO.salvar(cliente);
		
	}
	
	

}
