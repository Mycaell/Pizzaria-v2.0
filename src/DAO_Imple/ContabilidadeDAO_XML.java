package DAO_Imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DAO_Interfaces.ContablidadadeInterface;
import DTO.ContabilidadeDTO;

public class ContabilidadeDAO_XML implements ContablidadadeInterface{

//	TIRAR TODA LÓGICA QUE NÃO SEJA DE PERSISTÊNCIA E COLOCAR NO MODEL

//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	OBS 1: É POSSÍVEL QUE ALGUMAS DESSAS LÓGICAS NÃO SE ADEQUEM MAIS AO SISTEMA
//	OBS 2: EXISTEM ALGUNS MÉTODOS QUE AINDA NÃO POSSUEM LÓGICA EM XML
	
		
	
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivoContabilidade = new File("contabilidade.xml");

	private double[] contabilidade = this.recuperarContabilidade();
	

	
	@Override
	public ContabilidadeDTO getQtdVendas() {
		ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();
		contabilidadeDTO.setVendas((int) contabilidade[0]);
		return contabilidadeDTO;
	}

	@Override
	public ContabilidadeDTO getLucro() {
		ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();
		contabilidadeDTO.setLucro(contabilidade[1]);
		return contabilidadeDTO;
	}

//	daslkdsa
	
	public void salvarContabilidade(double[] contabilidade) {
		
//		contabilidade[0] = vendas
//		contabilidade[1] = lucro
				
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
	    xml += xstream.toXML(contabilidade);
		
		try {
			if(!arquivoContabilidade.exists())
				arquivoContabilidade.createNewFile();

			PrintWriter gravar = new PrintWriter(arquivoContabilidade);
			gravar.print(xml);
			gravar.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double[] recuperarContabilidade() {
		
		try {
			if(arquivoContabilidade.exists()) {
				FileInputStream fis = new FileInputStream(arquivoContabilidade);
				return (double[]) xstream.fromXML(fis);
			}else {
				double[] contabilidade = new double[2];
				salvarContabilidade(contabilidade);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return new double[2];
	}
	
	
	
	
}
