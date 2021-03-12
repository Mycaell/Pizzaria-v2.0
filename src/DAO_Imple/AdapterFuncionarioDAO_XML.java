package DAO_Imple;

import DAO_Interfaces.FuncionarioInterface;
import DTO.FuncionarioDTO;
import View_excecoes.FuncionarioExistenteException;
import View_excecoes.FuncionarioNaoExistenteException;

public class AdapterFuncionarioDAO_XML implements InterfaceInstrucoesAlvoFuncionario {

	private FuncionarioInterface bancoDeDados;
	
	public AdapterFuncionarioDAO_XML() {
		this.bancoDeDados = new FuncionarioDAO_XML();
	}

	@Override
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException {
		bancoDeDados.cadastrarFuncionario(funcionarioDTO);
	}

	@Override
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {
		bancoDeDados.excluirFuncionario(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO recuperarDadosDeTodosFuncionaros() {
		return bancoDeDados.recuperarDadosDeTodosFuncionaros();
	}

	@Override
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException {
		return bancoDeDados.recuperarFuncionario(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO recuperarFuncionarioPeloID(FuncionarioDTO funcionarioDTO) {
		return bancoDeDados.recuperarFuncionarioPeloID(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO getCodigo(FuncionarioDTO funcionarioDTO) {
		return bancoDeDados.getCodigo(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO getCargo(FuncionarioDTO funcionarioDTO) {
		return bancoDeDados.getCargo(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO getNome(FuncionarioDTO funcionarioDTO) {
		return bancoDeDados.getNome(funcionarioDTO);
	}

}
