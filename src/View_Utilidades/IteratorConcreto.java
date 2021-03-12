package View_Utilidades;

import java.util.ArrayList;

import DTO.ClienteDTO;

public class IteratorConcreto implements View_Utilidades.Iterator {


	private ArrayList<ClienteDTO> lista;
	private int posicao;
	
	public IteratorConcreto(ArrayList<ClienteDTO> lista) {
		this.lista = lista;
		posicao = 0;
	}

	@Override
	public boolean hasNext() {
		if(posicao >= lista.size() || lista.get(posicao) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object objeto = lista.get(posicao);
		posicao++;
		return objeto;
	}


}
