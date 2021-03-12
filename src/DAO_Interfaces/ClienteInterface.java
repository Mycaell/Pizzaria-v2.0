package DAO_Interfaces;



import DTO.ClienteDTO;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public interface ClienteInterface {
	

	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException;
	public void excluirCliente(ClienteDTO clienteDTO);
	public ClienteDTO recuperarDadosDeTodosOsClientes();
	public ClienteDTO getEndereco(ClienteDTO clienteDTO);
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException ;
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO);
	
}
