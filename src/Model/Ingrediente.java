package Model;

import java.util.ArrayList;

import DAO_Imple.AdapterIngredienteDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoIngrediente;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public abstract class Ingrediente {

	
	private int id;
	private String nome;
	private float preco;
	
	private ArrayList<String> nomes = new ArrayList<>();
	
//	private IngredienteInterface ingredienteDAO = new IngredienteDAO_Postgre();
	
	private InterfaceInstrucoesAlvoIngrediente adapterIngredienteDAO = new AdapterIngredienteDAO_Postgress();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
	
	public final void adicionarIngrediente(IngredienteDTO ingredienteDTO) {
		adapterIngredienteDAO.adicionarIngrediente(ingredienteDTO);
	}
	
	public final void removerIngrediente(IngredienteDTO ingredienteDTO) {
		adapterIngredienteDAO.removerIngrediente(ingredienteDTO);
	}

	
	public IngredienteDTO recuperarIngredientesDeUmaPizza(PizzaDTO pizzaDTO) {
		return adapterIngredienteDAO.recuperarIngredientesDeUmaPizza(pizzaDTO);
	}
	
	
	public final IngredienteDTO recuperarTodosIngredientes() {
		return adapterIngredienteDAO.recuperarTodosIngredientes();
	}
	
	public final IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO) {
		return adapterIngredienteDAO.recuperarIngredientePorID(ingredienteDTO);
	}
	
	public ArrayList<String> getNomes() {
		return nomes;
	}

	public void setNomes(String nome) {
		nomes.add(nome);
	}
	
	
	
	
	
}
