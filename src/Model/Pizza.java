package Model;

import DAO_Imple.AdapterPizzaDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoPizza;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public class Pizza {

	private String sabor;
	private float precoProFatia;
	private int qtdVendida;
	private int codigoDoGerenteQueCadastrou;
	private float custoPreparo;
	
	
//	private int tempoDeFornoEmMinutos;
//	private int temperaturaDeForno;
//	private String encaixotamento;
	
//	private PizzaInterface pizzaDAO = new PizzaDAO_Postgre();
	
//	private InterfaceInstrucoesAlvoPizza adapterPizzaDAO = new AdapterPizzaDAO_Postgress();
	private InterfaceInstrucoesAlvoPizza adapterPizzaDAO = new AdapterPizzaDAO_Postgress();
	
	public Pizza() {
		
	}
	
	public Pizza(String sabor) {
		this.sabor = sabor;
	}

	public Pizza(String sabor, float precoPorFatia, int codigoDoGerenteQueCadastrou) {
		this.sabor = sabor;
		this.precoProFatia = precoPorFatia;
		this.codigoDoGerenteQueCadastrou = codigoDoGerenteQueCadastrou;
	}
	
	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public float getPrecoProFatia() {
		return precoProFatia;
	}

	public void setPrecoProFatia(float precoProFatia) {
		this.precoProFatia = precoProFatia;
	}

	public int getQtdVendida() {
		return qtdVendida;
	}

	public void setQtdVendida(int qtdVendida) {
		this.qtdVendida = qtdVendida;
	}

	public int getCodigoDoGerenteQueCadastrou() {
		return codigoDoGerenteQueCadastrou;
	}

	public void setCodigoDoGerenteQueCadastrou(int codigoDoGerenteQueCadastrou) {
		this.codigoDoGerenteQueCadastrou = codigoDoGerenteQueCadastrou;
	}
	
	public float getCustoPreparo() {
		return custoPreparo;
	}

	public void setCustoPreparo(float custoPreparo) {
		this.custoPreparo = custoPreparo;
	}
	
	public PizzaDTO recuperarCustoPreparoDePizza(PizzaDTO pizzaDTO) {
		return adapterPizzaDAO.recuperarCustoPreparoDePizza(pizzaDTO);
	}
	
	public PizzaDTO recuperarDadosDeTodasPizzas(){
		return adapterPizzaDAO.recuperarDadosDeTodasPizzas();
	}
	
	public void adicionarPizza(PizzaDTO pizzaDTO,  IngredienteDTO idto) {
		adapterPizzaDAO.adicionarPizza(pizzaDTO, idto);
	}
	
	public void removerPizza(PizzaDTO pizzaDTO) {
		adapterPizzaDAO.removerPizza(pizzaDTO);
	}

	public void editarPizza(PizzaDTO pizzaDTO) {
		adapterPizzaDAO.editarPizza(pizzaDTO);
	}

	
	
	public PizzaDTO trocarIdsPorSabores(PizzaDTO pizzaDTO) {
//		trocando os ids pelo nome do sabor de suas respectivas pizzas
		String[] idDosSabores = pizzaDTO.getIds().split(", ");
		String sabores = "";
		
		for(int j = 0; j < idDosSabores.length; j++) {
			pizzaDTO.setIdDaPizza(Integer.parseInt(idDosSabores[j]));
			sabores += adapterPizzaDAO.getSabor(pizzaDTO).getSabor()+", ";
		}
		
//		retirando os dois últimos caracteres (, ) da string sabores
		sabores = sabores.substring(0, sabores.length() - 2);
		
		pizzaDTO.setSabores(sabores);
		return pizzaDTO;
		
	}
	
	


	
	
	
//	public abstract void assar();
//	public abstract void encaixotar();
//	
	
	
	
	
}
