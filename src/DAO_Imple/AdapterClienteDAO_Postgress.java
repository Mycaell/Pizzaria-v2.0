package DAO_Imple;

import DAO_Interfaces.ClienteInterface;
import DTO.ClienteDTO;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;



public class AdapterClienteDAO_Postgress implements InterfaceInstrucoesAlvoCliente{

	private ClienteInterface bancoDeDados; 

	public AdapterClienteDAO_Postgress() {
		this.bancoDeDados = new ClienteDAO_Postgre();
	}
	
	@Override
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
		bancoDeDados.cadastrarCliente(clienteDTO);		
	}

	@Override
	public void excluirCliente(ClienteDTO clinteDTO) {
		bancoDeDados.excluirCliente(clinteDTO);		
	}

	@Override
	public ClienteDTO recuperarDadosDeTodosOsClientes() {
		return bancoDeDados.recuperarDadosDeTodosOsClientes();
	}

	@Override
	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {
		return bancoDeDados.getEndereco(clienteDTO);
	}

	@Override
	public ClienteDTO validarCPF(ClienteDTO clienteDTOs) throws CPFInexistenteException, CampoVazioException {
		return bancoDeDados.validarCPF(clienteDTOs);
	}

	@Override
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		return bancoDeDados.recuperarCPFDeTodosClientes(clienteDTO);
	}

}
