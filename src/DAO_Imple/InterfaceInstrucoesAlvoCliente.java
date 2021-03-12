package DAO_Imple;

import DTO.ClienteDTO;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public interface InterfaceInstrucoesAlvoCliente {

	
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException;
	public void excluirCliente(ClienteDTO clinteDTO);
	public ClienteDTO recuperarDadosDeTodosOsClientes();
	public ClienteDTO getEndereco(ClienteDTO clienteDTO);
	public ClienteDTO validarCPF(ClienteDTO clienteDTOs) throws CPFInexistenteException, CampoVazioException ;
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO);

	
}
