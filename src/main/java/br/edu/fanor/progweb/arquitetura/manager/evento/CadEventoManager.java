package br.edu.fanor.progweb.arquitetura.manager.evento;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.fanor.progweb.arquitetura.bussines.EventoBO;
import br.edu.fanor.progweb.arquitetura.entity.Eventos;
import br.edu.fanor.progweb.arquitetura.entity.Usuarios;
import br.edu.fanor.progweb.arquitetura.utils.MessagesUtils;

@RequestScoped
@ManagedBean(name = "cadEvento")
@Component(value = "cadEvento")
public class CadEventoManager {

	@Autowired
	private EventoBO eventoBO;
	@Autowired
	private ListEventoManager listEvent; // lista dos eventos cadastrados

	private String nome;
	private String curso;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private String diaSemana;
	private String periodicidade;
	private String responsavel;
	private String espaco;
	private String turno;
	private Boolean temAula;
	private Usuarios usuario;

	public String salvar() {
		Eventos evento = new Eventos();
		evento.setNome(nome);
		evento.setCurso(curso);
		evento.setDataInicial(dataInicial);
		evento.setDataFinal(dataFinal);
		evento.setDiaSemana(diaSemana);
		evento.setPeriodicidade(periodicidade);
		evento.setResponsavel(responsavel);
		evento.setEspaco(espaco);
		evento.setTurno(turno);
		evento.setTemAula(temAula);
		evento.setUsuarios(usuario);

		eventoBO.salvar(evento);
		
		listEvent.lista();

	//	return Navigation.SUCESSO;
		limpaDados();
		return "cadEvento";//?faces-redirect=true";
	}

	public void limpaDados() {
		this.nome = "";
		this.curso="";
		this.dataInicial = null;
		this.dataFinal = null;
		this.diaSemana = null;
		this.periodicidade = "";
		this.responsavel = "";
		this.espaco = "";
		this.turno = "";
		this.temAula = false;
		
		this.usuario = null;
	}

	public String preparaSalvar() {
		this.limpaDados();

		//return Navigation.SUCESSO;
		return "cadEvento?faces-redirect=true";
	}

	// GET AND SET
	public EventoBO getEventoBO() {
		return eventoBO;
	}

	public void setEventoBO(EventoBO eventoBO) {
		this.eventoBO = eventoBO;
	}

	public ListEventoManager getListEvent() {
		return listEvent;
	}

	public void setListEvent(ListEventoManager listEvent) {
		this.listEvent = listEvent;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getEspaco() {
		return espaco;
	}

	public void setEspaco(String espaco) {
		this.espaco = espaco;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Boolean getTemAula() {
		return temAula;
	}

	public void setTemAula(Boolean temAula) {
		this.temAula = temAula;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	
	
	
}
