package DAO_Imple;

import DTO.ClienteDTO;
import DTO.PedidoDTO;

public interface InterfaceInstrucoesAlvoPedido {

	
	public void adicionarPedido(PedidoDTO pedidoDTO);
	
	
	public void concluirPedido(PedidoDTO pedidoDTO);
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO);
	
	public PedidoDTO recuperarDadosDeTodosPedidosProntosParaPreparo();
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega();
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO);
	public PedidoDTO getPedidos();

	
}
