package br.edu.fanor.progweb.arquitetura.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.fanor.progweb.arquitetura.aspectj.Loggable;
import br.edu.fanor.progweb.arquitetura.aspectj.PermitAll;
import br.edu.fanor.progweb.arquitetura.aspectj.RolesAllowed;
import br.edu.fanor.progweb.arquitetura.dao.EventoDAO;
import br.edu.fanor.progweb.arquitetura.entity.Eventos;
import br.edu.fanor.progweb.arquitetura.exceptions.DAOException;
import br.edu.fanor.progweb.arquitetura.utils.MessagesUtils;

@Loggable
@Component
public class EventoBO {

	@Autowired
	private EventoDAO eventoDAO;

	@RolesAllowed(value = { "INCLUIR_EVENTO" })
	public void salvar(Eventos evento) {
	
	
		if(eventoDAO.buscaRepetido( evento.getTurno(),evento.getEspaco(), evento.getDiaSemana()) == null){
			eventoDAO.salvar(evento);
			MessagesUtils.info("Evento salvo com sucesso");
		}
		else{
			
			MessagesUtils.error("A sala ja esta ocupada");
			
		}
		
//		try{
//			if(eventoDAO.buscaRepetido( evento.getTurno(),evento.getEspaco(), evento.getDiaSemana()) == null){
//				eventoDAO.salvar(evento);
//				MessagesUtils.info("Evento salvo com sucesso");
//			}else{
//				MessagesUtils.error("A sala ja esta ocupada");
//			}
//		}catch(Exception e){
//			MessagesUtils.error("A sala ja esta ocupada");
//		}
	}

	@RolesAllowed(value = { "ALTERAR_EVENTO" })
	public void atualizar(Eventos evento) {
		eventoDAO.atualizar(evento);
	}

	@RolesAllowed(value = { "EXCLUIR_EVENTO" })
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void excluir(Eventos evento) {
		try {
			evento = eventoDAO.buscaPorId(evento.getId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		eventoDAO.excluir(evento);
	}

	@RolesAllowed(value = { "LISTAR_EVENTOS" })
	@Loggable(enable = false)
	public List<Eventos> listaEventoPorNome(String nome, String diaSemana, String turno) {
		List<Eventos> eventos = eventoDAO.listarPorNome(nome,diaSemana,turno);
		return eventos;
	}

	@PermitAll
	@Loggable(enable = false)
	public Eventos buscarPorId(Integer id) {
		try {
			return eventoDAO.buscaPorId(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
