package DAO_Imple;

import DAO_Interfaces.ContablidadadeInterface;
import DTO.ContabilidadeDTO;

public class AdapterContabilidadeDAO_Postgress implements InterfaceInstrucoesAlvoContabilidade {

	private ContablidadadeInterface bancoDeDados; 

	
	
	public AdapterContabilidadeDAO_Postgress() {
		this.bancoDeDados = new ContabilidadeDAO_Postgre();
	}

	@Override
	public ContabilidadeDTO getLucro() {
		return bancoDeDados.getLucro();
	}

	@Override
	public ContabilidadeDTO getQtdVendas() {
		return bancoDeDados.getQtdVendas();
	}

}
