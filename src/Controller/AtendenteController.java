package Controller;


import DTO.ClienteDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import Model.Atendente;
import Model.Cliente;
import Model.Pedido;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public class AtendenteController {
	
	private Atendente atendente = new Atendente();
	private Cliente cliente = new Cliente();
	private Pedido pedido = new Pedido();
	
	
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO){
		return cliente.recuperarCPFDeTodosClientes(clienteDTO);
	}

	
	public PedidoDTO recuperarDadosDeTodosPedidos(){
		return atendente.recuperarDadosDeTodosPedidos();
	}
	
	public PizzaDTO recuperarSaboresDePizzas(){
		return atendente.getSaboresDePizzas();
	}
 	
	
	public PizzaDTO recuperarPrecoDaFatia(PizzaDTO pizzaDTO) {
		return atendente.getPrecoDaFatia(pizzaDTO);
	}
	
	public ClienteDTO recuperarEndereco(ClienteDTO clienteDTO) {
		return cliente.getEndereco(clienteDTO);
	}
	
	public void adicionarPedido(PedidoDTO pedidoDTO) {
		atendente.adicionarPedido(pedidoDTO);
	}
	
	
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
		cliente.cadastrarCliente(clienteDTO);
	}
	
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {
		return cliente.validarCPF(clienteDTO);
	}

	
	
	public PedidoDTO calcularPrecoDoPedido(PedidoDTO pedidoDTO) {
		return pedido.calcularPrecoDoPedido(pedidoDTO);
	}
	
	
	
	
	

}
