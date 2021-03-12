package DAO_Interfaces;

import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public interface IngredienteInterface {

	
	public void adicionarIngrediente(IngredienteDTO ingredienteDTO);
	public void removerIngrediente(IngredienteDTO ingredienteDTO);
	
	
	public IngredienteDTO recuperarTodosIngredientes();
	public IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO);
	public IngredienteDTO recuperarIngredientesDeUmaPizza(PizzaDTO pizzaDTO);
}
