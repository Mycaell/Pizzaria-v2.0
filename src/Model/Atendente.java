package Model;


import DAO_Imple.AdapterPedidoDAO_Postgress;
import DAO_Imple.AdapterPizzaDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoPedido;
import DAO_Imple.InterfaceInstrucoesAlvoPizza;
import DTO.PedidoDTO;
import DTO.PizzaDTO;

public class Atendente extends Funcionario{
	

//	private PedidoInterface PDP = new PedidoDAO_Postgre();
//	private PizzaInterface pizzaDAO = new PizzaDAO_Postgre();
	
	private InterfaceInstrucoesAlvoPedido adapterPedidoDAO = new AdapterPedidoDAO_Postgress();
	
	private InterfaceInstrucoesAlvoPizza adapterPizzaDAO = new AdapterPizzaDAO_Postgress();
	
	
	public void adicionarPedido(PedidoDTO pedidoDTO) {
		adapterPedidoDAO.adicionarPedido(pedidoDTO);
	}

	public PedidoDTO recuperarDadosDeTodosPedidos(){
		return adapterPedidoDAO.getPedidos();
	}
	
	public PizzaDTO getSaboresDePizzas(){
		return adapterPizzaDAO.getSaboresDePizzas();
	}
	
	public PizzaDTO getPrecoDaFatia(PizzaDTO pizzaDTO) {
		return adapterPizzaDAO.getPrecoDaFatia(pizzaDTO);
	}

//	public PizzaDTO getSaborDePizza(PizzaDTO pizzaDTO) {
//		return pizzaDAO.getSabor(pizzaDTO);
//	}
//	
	
	

	
	
	
	
	
	
	
	
}	