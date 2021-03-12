package DAO_Imple;

import DAO_Interfaces.IngredienteInterface;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public class AdapterIngredienteDAO_XML implements InterfaceInstrucoesAlvoIngrediente {

	private IngredienteInterface bancoDeDados;

	public AdapterIngredienteDAO_XML() {
		this.bancoDeDados = new IngredienteDAO_XML();
	}

	@Override
	public void adicionarIngrediente(IngredienteDTO ingredienteDTO) {
		bancoDeDados.adicionarIngrediente(ingredienteDTO);
	}

	@Override
	public void removerIngrediente(IngredienteDTO ingredienteDTO) {
		bancoDeDados.removerIngrediente(ingredienteDTO);
	}

	@Override
	public IngredienteDTO recuperarTodosIngredientes() {
		return bancoDeDados.recuperarTodosIngredientes();
	}

	@Override
	public IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO) {
		return bancoDeDados.recuperarIngredientePorID(ingredienteDTO);
	}

	@Override
	public IngredienteDTO recuperarIngredientesDeUmaPizza(PizzaDTO pizzaDTO) {
		return bancoDeDados.recuperarIngredientesDeUmaPizza(pizzaDTO);
	}
	
	
	
	
}
