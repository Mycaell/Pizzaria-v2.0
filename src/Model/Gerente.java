package Model;

import DAO_Imple.AdapterFuncionarioDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoFuncionario;
import DTO.FuncionarioDTO;
import View_excecoes.FuncionarioExistenteException;
import View_excecoes.FuncionarioNaoExistenteException;

public class Gerente extends Funcionario{

	
	private InterfaceInstrucoesAlvoFuncionario adapterFuncionarioDAO = new AdapterFuncionarioDAO_Postgress();


	
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException{
		return adapterFuncionarioDAO.recuperarFuncionario(funcionarioDTO);
	}

	public FuncionarioDTO recuperarCodigo(FuncionarioDTO funcionarioDTO) {
		return adapterFuncionarioDAO.getCodigo(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarCargo(FuncionarioDTO funcionarioDTO) {
		return adapterFuncionarioDAO.getCargo(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarNome(FuncionarioDTO funcionarioDTO) {
		return adapterFuncionarioDAO.getNome(funcionarioDTO);
	}
	
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException {
		adapterFuncionarioDAO.cadastrarFuncionario(funcionarioDTO);
	}


	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {
		adapterFuncionarioDAO.excluirFuncionario(funcionarioDTO);
	}
	
	public FuncionarioDTO recuperarDadosDeTodosFuncionaros(){
		return adapterFuncionarioDAO.recuperarDadosDeTodosFuncionaros();
	}
	
	public FuncionarioDTO recuperarFuncionarioPeloID(FuncionarioDTO funcionarioDTO) {
		return adapterFuncionarioDAO.recuperarFuncionarioPeloID(funcionarioDTO);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
