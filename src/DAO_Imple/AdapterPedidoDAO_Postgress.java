package DAO_Imple;

import DAO_Interfaces.PedidoInterface;
import DTO.ClienteDTO;
import DTO.PedidoDTO;

public class AdapterPedidoDAO_Postgress implements InterfaceInstrucoesAlvoPedido {

	private PedidoInterface bancoDeDados;
	
	public AdapterPedidoDAO_Postgress() {
		this.bancoDeDados = new PedidoDAO_Postgre();
	}

	@Override
	public void adicionarPedido(PedidoDTO pedidoDTO) {
		bancoDeDados.adicionarPedido(pedidoDTO);
	}

	@Override
	public void concluirPedido(PedidoDTO pedidoDTO) {
		bancoDeDados.concluirPedido(pedidoDTO);
	}

	@Override
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {
		bancoDeDados.realizarEntregaDePedido(pedidoDTO);
	}

	@Override
	public PedidoDTO recuperarDadosDeTodosPedidosProntosParaPreparo() {
		return bancoDeDados.recuperarDadosDeTodosPedidosProntosParaPreparo();
	}

	@Override
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega() {
		return bancoDeDados.recuperarDadosDetodosPedidosProntosParaEntrega();
	}

	@Override
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO) {
		return bancoDeDados.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO);
	}

	@Override
	public PedidoDTO getPedidos() {
		return bancoDeDados.getPedidos();
	}

}
