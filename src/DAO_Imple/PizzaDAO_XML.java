package DAO_Imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DAO_Interfaces.PizzaInterface;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public class PizzaDAO_XML implements PizzaInterface{

//	TIRAR TODA LÓGICA QUE NÃO SEJA DE PERSISTÊNCIA E COLOCAR NO MODEL

//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	OBS 1: É POSSÍVEL QUE ALGUMAS DESSAS LÓGICAS NÃO SE ADEQUEM MAIS AO SISTEMA
//	OBS 2: EXISTEM ALGUNS MÉTODOS QUE AINDA NÃO POSSUEM LÓGICA EM XML
	
	
	
	
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivoPizzas = new File("pizzas.xml");
	
	
	private ArrayList<String[]> pizzas = this.recuperarPizzas();
	
//	@Override
//	public void adicionarPizza(PizzaDTO pizzaDTO) {
//		
////		pizza[0] = sabor;
////		pizza[1] = preço por fatia ;
////		pizza[2] = quantidade vendida;
////		pizza[3] = codigoDoGerenteQueCadastrou;
////		pizza[4] = idDaPizza;
////		
//		
//		String[] pizza = {pizzaDTO.getSabor(), Float.toString(pizzaDTO.getPrecoProFatia()), Integer.toString(pizzaDTO.getQtdVendida()), Integer.toString(pizzaDTO.getCodigoDoGerenteQueCadastrou()), Integer.toString(pizzaDTO.getIdDaPizza())};
//
//		pizzas.add(pizza);
//		this.salvarPizzas(pizzas);
//		JOptionPane.showMessageDialog(null, "Pizza Adicionada!");
//	}


	
	@Override
	public void editarPizza(PizzaDTO pizzaDTO) {
		for (String[] pizza : pizzas) {
			if(pizza[0].equals(pizzaDTO.getSabor())) {
				pizza[0] = pizzaDTO.getNovoSabor();
				pizza[1] = Float.toString(pizzaDTO.getNovoPrecoPorFatia());
				
				this.salvarPizzas(pizzas);
				
				JOptionPane.showMessageDialog(null, "Edição Concluída!");
				break;
			}
		}
	}


	@Override
	public void removerPizza(PizzaDTO pizzaDTO) {
		for (String[] pizza : pizzas) {
			if(pizza[0].equals(pizzaDTO.getSabor())) {
				pizzas.remove(pizza);
				this.salvarPizzas(pizzas);
				JOptionPane.showMessageDialog(null, "Pizza Removida!");
				break;
			}
		}
	}

	
	@Override
	public PizzaDTO getSaboresDePizzas() {
		ArrayList<String> saboresDePizzas = new ArrayList<String>();
		
		for (String[] pizza : pizzas) {
			saboresDePizzas.add(pizza[0]);
		}
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setSaboresDePizzas(saboresDePizzas);
		return pizzaDTO;
	}

	@Override
	public PizzaDTO getPrecoDaFatia(PizzaDTO pizzaDTO) {
		for (String[] pizza : pizzas) {
			if(pizza[0].equals(pizzaDTO.getSabor())) {
				pizzaDTO.setPrecoProFatia(Float.parseFloat(pizza[1]));
				break;
			}
		}
		return pizzaDTO;
	}

	@Override
	public PizzaDTO recuperarDadosDeTodasPizzas() {
		ArrayList<String[]> dadosDasPizzas = new ArrayList<String[]>();
		
		for (String[] pizza : pizzas) {
			String[] linha = new String[3];
			linha[0] = pizza[0];
			linha[1] = pizza[1];
			linha[2] = pizza[2];
			dadosDasPizzas.add(linha);
		}
	
		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setDadosDasPizzas(dadosDasPizzas);
		return pizzaDTO;
	}
	
	
	public void salvarPizzas(ArrayList<String[]> pizzas) {
		String xml = "<?xml version =\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		xml += xstream.toXML(pizzas);
		
		try {
			if(!arquivoPizzas.exists())
				arquivoPizzas.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivoPizzas);
			gravar.print(xml);
			gravar.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> recuperarPizzas() {
		try {
			if(arquivoPizzas.exists()) {
				FileInputStream fis = new FileInputStream(arquivoPizzas);
				return (ArrayList<String[]>) xstream.fromXML(fis);
			}else {
				ArrayList<String[]> pizzas = new ArrayList<>();
				this.salvarPizzas(pizzas);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String[]>();
			
	}



	@Override
	public void adicionarPizza(PizzaDTO pizzaDTO, IngredienteDTO idto) {
		// TODO Auto-generated method stub
	}



	@Override
	public PizzaDTO getSabor(PizzaDTO pizzaDTO) {

		return null;
	}



	@Override
	public PizzaDTO recuperarCustoPreparoDePizza(PizzaDTO pizzaDTO) {
		return null;
	}




}
