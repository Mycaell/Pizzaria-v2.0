package Model;

import DAO_Imple.AdapterContabilidadeDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoContabilidade;
import DTO.ContabilidadeDTO;

public class Contabilidade {

//	DEVE HAVER UM ID AQUI;
	private int vendas;
	private double lucro;
	
	private InterfaceInstrucoesAlvoContabilidade adapterContabilidadeDAO = new AdapterContabilidadeDAO_Postgress();
//	private ContablidadadeInterface contabilidadeDAO = new ContabilidadeDAO_Postgre();
	
	public int getVendas() {
		return vendas;
	}
	public void setVendas(int vendas) {
		this.vendas = vendas;
	}
	public double getLucro() {
		return lucro;
	}
	public void setLucro(double lucro) {
		this.lucro = lucro;
	}


	public ContabilidadeDTO recuperarLucro() {
		return adapterContabilidadeDAO.getLucro();
	}
	
	public ContabilidadeDTO getQtdVendas() {
		return adapterContabilidadeDAO.getQtdVendas();
	}
	
	
	
}
