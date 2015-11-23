package br.edu.fanor.progweb.arquitetura.conversores;



import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.edu.fanor.progweb.arquitetura.dao.UsuarioDAO;
import br.edu.fanor.progweb.arquitetura.entity.Usuarios;

@SuppressWarnings("serial")
public class ConverterUsuario implements Converter, Serializable {

	private UsuarioDAO uDAO= null;
	// converte da tela para o objeto 
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione um funcionario")){
			return null;
		}		
		
		return uDAO.getEntityManager().find(Usuarios.class,Integer.parseInt(string));  
				
		
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null){
			return null;
		}
		Usuarios obj = (Usuarios) o;
		return obj.getId().toString();
	}

}
