package br.edu.fanor.progweb.arquitetura.manager.evento;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.fanor.progweb.arquitetura.bussines.EventoBO;
import br.edu.fanor.progweb.arquitetura.entity.Eventos;
import br.edu.fanor.progweb.arquitetura.utils.Navigation;

@RequestScoped
@ManagedBean(name = "listEvento")
@Component(value = "listEvento")
public class ListEventoManager {

	@Autowired
	private EventoBO eventoBO;

	private List<Eventos> eventos;

	private String nome;
	private String diaSemana;
	private String turno;

	public void lista() {
		eventos = eventoBO.listaEventoPorNome(nome,diaSemana,turno);
	}

	public void excluir(Eventos evento) {
		eventoBO.excluir(evento);
		eventos = eventoBO.listaEventoPorNome(nome,diaSemana,turno);
	}

	public String preparaAtualizar(Eventos evento) {
		System.out.println(evento.getNome());
		return null;
	}

	public void limparDados() {
		this.nome = "";
		
		this.eventos = null;
	}

	public String preparaListar() {
		this.limparDados();
		return Navigation.SUCESSO;
	}

	public String salvar(){
		return null;
	}
	
	
	// GET AND SET
	public EventoBO getEventoBO() {
		return eventoBO;
	}

	public void setEventoBO(EventoBO eventoBO) {
		this.eventoBO = eventoBO;
	}

	public List<Eventos> getEventos() {
		return eventos;
	}

	public void setEventos(List<Eventos> eventos) {
		this.eventos = eventos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	
	
}
