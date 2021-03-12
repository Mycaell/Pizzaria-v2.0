package DAO_Imple;

import DAO_Interfaces.ClienteInterface;
import DTO.ClienteDTO;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public class AdapterClienteDAO_XML implements InterfaceInstrucoesAlvoCliente{

	private ClienteInterface bancoDeDados; 
	

	public AdapterClienteDAO_XML() {
		bancoDeDados = new ClienteDAO_XML();
	}
	
	@Override
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException{
		bancoDeDados.cadastrarCliente(clienteDTO);
	}

	
	@Override
	public void excluirCliente(ClienteDTO clienteDTO) {
		bancoDeDados.excluirCliente(clienteDTO);
	}
	
	@Override
	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {
		return bancoDeDados.getEndereco(clienteDTO);
	}


	@Override
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {
		return bancoDeDados.validarCPF(clienteDTO);
	}
	
	@Override
	public ClienteDTO recuperarDadosDeTodosOsClientes() {
		return bancoDeDados.recuperarDadosDeTodosOsClientes();
	}


	@Override
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		return null;
//		não implementado
	}

}
