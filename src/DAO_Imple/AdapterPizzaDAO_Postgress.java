package DAO_Imple;

import DAO_Interfaces.PizzaInterface;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public class AdapterPizzaDAO_Postgress implements InterfaceInstrucoesAlvoPizza {

	private PizzaInterface bancoDeDados;
	
	public AdapterPizzaDAO_Postgress() {
		this.bancoDeDados = new PizzaDAO_Postgre();
	}

	@Override
	public void editarPizza(PizzaDTO pizzaDTO) {
		bancoDeDados.editarPizza(pizzaDTO);
	}

	@Override
	public void adicionarPizza(PizzaDTO pizzaDTO, IngredienteDTO idto) {
		bancoDeDados.adicionarPizza(pizzaDTO, idto);		
	}

	@Override
	public void removerPizza(PizzaDTO pizzaDTO) {
		bancoDeDados.removerPizza(pizzaDTO);		
	}

	@Override
	public PizzaDTO getSaboresDePizzas() {
		return bancoDeDados.getSaboresDePizzas();
	}

	@Override
	public PizzaDTO getSabor(PizzaDTO pizzaDTO) {
		return bancoDeDados.getSabor(pizzaDTO);
	}

	@Override
	public PizzaDTO getPrecoDaFatia(PizzaDTO pizzaDTO) {
		return bancoDeDados.getPrecoDaFatia(pizzaDTO);
	}

	@Override
	public PizzaDTO recuperarCustoPreparoDePizza(PizzaDTO pizzaDTO) {
		return bancoDeDados.recuperarCustoPreparoDePizza(pizzaDTO);
	}

	@Override
	public PizzaDTO recuperarDadosDeTodasPizzas() {
		return bancoDeDados.recuperarDadosDeTodasPizzas();
	}

}
