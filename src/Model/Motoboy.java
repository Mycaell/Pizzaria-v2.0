package Model;


import DAO_Imple.AdapterPedidoDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoPedido;
import DTO.PedidoDTO;


public class Motoboy extends Funcionario{


//	private PedidoInterface PDP = new PedidoDAO_Postgre();
	
	private InterfaceInstrucoesAlvoPedido adapterPedidoDAO = new AdapterPedidoDAO_Postgress();
	
	
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega(){
		return adapterPedidoDAO.recuperarDadosDetodosPedidosProntosParaEntrega();
	}
	
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {
		adapterPedidoDAO.realizarEntregaDePedido(pedidoDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
