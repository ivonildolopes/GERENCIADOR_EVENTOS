package br.edu.fanor.progweb.arquitetura.manager.evento;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.fanor.progweb.arquitetura.bussines.EventoBO;
import br.edu.fanor.progweb.arquitetura.entity.Eventos;
import br.edu.fanor.progweb.arquitetura.utils.MessagesUtils;

@RequestScoped
@ManagedBean(name = "atualizaEvento")
@Component(value = "atualizaEvento")
public class AtualizaEventoManager {

	@Autowired
	private EventoBO eventoBo;
	private Eventos eventoSelecionado;

	public String atualizar() {

		//try {
			eventoBo.atualizar(eventoSelecionado);
			MessagesUtils.info("Evento atualizado com sucesso!");
		//} catch (Exception e) {
			//MessagesUtils.error("Não foi possivel alterar o evento");
		//}

		return "listEvento?faces-redirect=true";
	}
	
	public String preperaAtualizar(Eventos evento){
		eventoSelecionado = eventoBo.buscarPorId(evento.getId());
		return "atualizaEvento?faces-redirect=true";
	}
	
	public void limparDados(){
		eventoSelecionado.setNome("");
		eventoSelecionado.setCurso("");
		eventoSelecionado.setDiaSemana("");
		eventoSelecionado.setPeriodicidade("");
		eventoSelecionado.setResponsavel("");
		eventoSelecionado.setEspaco("");
		eventoSelecionado.setTurno("");
		eventoSelecionado.setTemAula(false);
		
	}

	public Eventos getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Eventos eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}
	

}
