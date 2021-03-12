package Controller;



import DTO.ClienteDTO;
import DTO.ContabilidadeDTO;
import DTO.FuncionarioDTO;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;
import Model.Adicional;
import Model.Cliente;
import Model.Contabilidade;
import Model.Gerente;
import Model.Ingrediente;
import Model.Massa;
import Model.Pedido;
import Model.Pizza;
import View_excecoes.FuncionarioExistenteException;
import View_excecoes.FuncionarioNaoExistenteException;


public class GerenteController {
	
	private Gerente gerente = new Gerente();
	private Cliente cliente = new Cliente();
	private Pizza pizza = new Pizza();
	private Pedido pedido = new Pedido();
	private Contabilidade contabilidade = new Contabilidade();
	private Ingrediente massa = new Massa();
	
	
	public FuncionarioDTO recuperarFuncionarioPeloID(FuncionarioDTO funcionarioDTO) {
		return gerente.recuperarFuncionarioPeloID(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException{
		return gerente.recuperarFuncionario(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarCodigo(FuncionarioDTO funcionarioDTO) {
		return gerente.recuperarCodigo(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarCargo(FuncionarioDTO funcionarioDTO) {
		return gerente.recuperarCargo(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarNome(FuncionarioDTO funcionarioDTO) {
		return gerente.recuperarNome(funcionarioDTO);
	}
	
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException{	
		gerente.cadastrarFuncionario(funcionarioDTO);
	}
	
	
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {
		gerente.excluirFuncionario(funcionarioDTO);
	}
	

	public FuncionarioDTO recuperarDadosDeTodosFuncionarios(){
		return gerente.recuperarDadosDeTodosFuncionaros();
	}

	public void excluirCliente(ClienteDTO clienteDTO) {
		cliente.excluirCliente(clienteDTO);
	}
	
	public ClienteDTO recuperarDadosDeTodosOsClientes(){
		return cliente.recuperarDadosDeTodosOsClientes();
	}
	
	
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO){
		return pedido.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO);
	}
	

	public PizzaDTO recuperarCustoPreparoDePizza(PizzaDTO pizzaDTO) {
		return pizza.recuperarCustoPreparoDePizza(pizzaDTO);
	}
	
	public PizzaDTO recuperarDadosDeTodasPizzas() {
		return pizza.recuperarDadosDeTodasPizzas();
	}
	
	public void adicionarPizza(PizzaDTO pizzaDTO,  IngredienteDTO idto) {
		pizza.adicionarPizza(pizzaDTO, idto);
	}
	
	public void removerPizza(PizzaDTO pizzaDTO) {
		pizza.removerPizza(pizzaDTO);
	}
	
	
	public void editarPizza(PizzaDTO pizzaDTO) {
		pizza.editarPizza(pizzaDTO);
	}
	
	public PizzaDTO getSabores(PizzaDTO pizzaDTO) {
		return pizza.trocarIdsPorSabores(pizzaDTO);
	}
	
	
	public ContabilidadeDTO getQtdVendas() {
		return contabilidade.getQtdVendas();
	}

	public ContabilidadeDTO getLucro() {
		return contabilidade.recuperarLucro();
	}

	
	public IngredienteDTO recuperarIngredientesDeUmaPizza(PizzaDTO pizzaDTO) {
		return massa.recuperarIngredientesDeUmaPizza(pizzaDTO);
	}
	
	public IngredienteDTO recuperarIngredientes() {
		return massa.recuperarTodosIngredientes();
	}
	
	public IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO) {
		return massa.recuperarIngredientePorID(ingredienteDTO);
	}
	
	
	public void adicionarIngrediente(IngredienteDTO ingredienteDTO) {
		massa.adicionarIngrediente(ingredienteDTO);
	}
	
	public void removerIngrediente(IngredienteDTO ingredienteDTO) {
		massa.removerIngrediente(ingredienteDTO);
	}
	

	/**
	 * m�todo respons�vel por respons�vel por realizar a decora��o 
	 * */
	public IngredienteDTO decorar(IngredienteDTO idto) {	
	
		Ingrediente ingredientes = new Massa();
		
		for (String[] i : idto.getIngredientes()) {
			ingredientes = new Adicional(ingredientes, i[0], Float.parseFloat(i[1]));
		}
		
		idto.setPreco(ingredientes.getPreco());
		idto.setNomes(ingredientes.getNomes());

		return idto;
	}
	
	
}
