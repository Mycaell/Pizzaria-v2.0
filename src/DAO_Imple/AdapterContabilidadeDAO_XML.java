package DAO_Imple;

import DAO_Interfaces.ContablidadadeInterface;
import DTO.ContabilidadeDTO;

public class AdapterContabilidadeDAO_XML implements InterfaceInstrucoesAlvoContabilidade {

	private ContablidadadeInterface bancoDeDados;
	
	public AdapterContabilidadeDAO_XML() {
		this.bancoDeDados = new ContabilidadeDAO_XML();
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
