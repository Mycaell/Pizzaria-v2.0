package Model;


import DAO_Imple.AdapterPedidoDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoPedido;
import DTO.PedidoDTO;

public class Pizzaiolo extends Funcionario{


//	private PedidoInterface PDP = new PedidoDAO_Postgre();
	
	private InterfaceInstrucoesAlvoPedido adapterPedidoDAO = new AdapterPedidoDAO_Postgress();
	
	public PedidoDTO recuperarDadosDeTodosPedidosProntos(){
		return adapterPedidoDAO.recuperarDadosDeTodosPedidosProntosParaPreparo();
	}
	
	public void concluirPedido(PedidoDTO pedidoDTO) {
		adapterPedidoDAO.concluirPedido(pedidoDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
