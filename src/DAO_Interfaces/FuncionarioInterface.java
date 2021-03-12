package DAO_Interfaces;


import DTO.FuncionarioDTO;
import View_excecoes.FuncionarioExistenteException;
import View_excecoes.FuncionarioNaoExistenteException;

public interface FuncionarioInterface {

	
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException;
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO);

	public FuncionarioDTO recuperarDadosDeTodosFuncionaros();
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException;
	public FuncionarioDTO recuperarFuncionarioPeloID(FuncionarioDTO funcionarioDTO);
	
	
	public FuncionarioDTO getCodigo(FuncionarioDTO funcionarioDTO);
	public FuncionarioDTO getCargo(FuncionarioDTO funcionarioDTO);
	public FuncionarioDTO getNome(FuncionarioDTO funcionarioDTO);
	
		
}
