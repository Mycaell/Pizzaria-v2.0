package DAO_Imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DAO_Interfaces.FuncionarioInterface;
import DTO.FuncionarioDTO;
import View_excecoes.FuncionarioExistenteException;
import View_excecoes.FuncionarioNaoExistenteException;

public class FuncionarioDAO_XML implements FuncionarioInterface{

	
//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	OBS 1: É POSSÍVEL QUE ALGUMAS DESSAS LÓGICAS NÃO SE ADEQUEM MAIS AO SISTEMA
//	OBS 2: EXISTEM ALGUNS MÉTODOS QUE AINDA NÃO POSSUEM LÓGICA EM XML
	
	
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivoFuncionarios = new File("funcionarios.xml");
	
	
	private ArrayList<String[]> funcionarios = this.recuperarFuncionarios();

	
	
	@Override
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException {
		
//		funcionario[0] = nome;
//		funcionario[1] = sobrenome;
//		funcionario[2] = sexo;
//		funcionario[3] = dataDeNascimento;
//		funcionario[4] = endereço;
//		funcionario[5] = telefone;
//		funcionario[6] = cpf;
//		funcionario[7] = email;
//		funcionario[8] = senha;
//		funcionario[9] = cargo;
//		funcionario[10] = codigo;
//		funcionario[11] = codigoDoFuncionarioQueRealizouOCadastro;

		for (String[] f : funcionarios) {
			if(f[7].equals(funcionarioDTO.getEmail())) {
				throw new FuncionarioExistenteException();
			}
		}
		
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String[] funcionario = {funcionarioDTO.getNome(), funcionarioDTO.getSobrenome(), String.valueOf(funcionarioDTO.getSexo()), formataData.format(funcionarioDTO.getDataDeNascimento()), funcionarioDTO.getEndereço(), funcionarioDTO.getTelefone(), funcionarioDTO.getCPF(), funcionarioDTO.getEmail(), funcionarioDTO.getSenha(), funcionarioDTO.getCargo(), Integer.toString(funcionarioDTO.getCodigo()), Integer.toString(funcionarioDTO.getCodigoDoFuncionarioQueRealizouOCadastro())};
		funcionarios.add(funcionario);
		salvarFuncionarios(funcionarios);
	}

	@Override
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {
		for (String[] funcionario : funcionarios) {
			if(Integer.parseInt(funcionario[10]) == funcionarioDTO.getCodigo()) {
				funcionarios.remove(funcionario);
				salvarFuncionarios(funcionarios);
				JOptionPane.showMessageDialog(null, "Funcionário Excluído!");
				break;
			}
		}
	}




	


	@Override
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException {
		for (String[] funcionario: funcionarios) {
			if(funcionario[7].equals(funcionarioDTO.getEmail()) && funcionario[8].equals(funcionarioDTO.getSenha())) {
//				return funcionario[9];
				funcionarioDTO.setCargo(funcionario[9]);
				return funcionarioDTO;
			}
		}
		throw new FuncionarioNaoExistenteException();
	}

	@Override
	public FuncionarioDTO getCodigo(FuncionarioDTO funcionarioDTO) {
		int codigo = 0;
		for (String[] funcionario: funcionarios) {
			if(funcionario[7].equals(funcionarioDTO.getEmail())) {
				codigo = Integer.parseInt(funcionario[10]);
				break;
			}
		}
		
		funcionarioDTO.setCodigo(codigo);
		return funcionarioDTO;
	}

	@Override
	public FuncionarioDTO getCargo(FuncionarioDTO funcionarioDTO) {
		String cargo = null;
		for (String[] funcionario: funcionarios) {
			if(funcionario[10].equals(Integer.toString(funcionarioDTO.getCodigo()))) {
				cargo = funcionario[9];
				break;
			}
		}

		funcionarioDTO.setCargo(cargo);
		return funcionarioDTO;
	}
	
	@Override
	public FuncionarioDTO getNome(FuncionarioDTO funcionarioDTO) {
		String nome = null;
		for (String[] funcionario: funcionarios) {
			if(funcionario[10].equals(Integer.toString(funcionarioDTO.getCodigo()))) {
				nome = funcionario[0];
				break;
			}
		}
		funcionarioDTO.setNome(nome);
		return funcionarioDTO;
	}

	@Override
	public FuncionarioDTO recuperarDadosDeTodosFuncionaros() {
		ArrayList<String[]> dadosDosFuncionarios = new ArrayList<String[]>();
		
		for (String[] funcionario : funcionarios) {
			String[] linha = new String[3];
//			posição do nome
			linha[0] = funcionario[0];
//			posição do código
			linha[1] = funcionario[10];
//			posição do cargo
			linha[2] = funcionario[9];
			dadosDosFuncionarios.add(linha);
		}
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setDadosDosFuncionarios(dadosDosFuncionarios);
		return funcionarioDTO;
	}
	



	
	
	public void salvarFuncionarios(ArrayList<String[]> funcionarios) {
		String xml = "<?xml version =\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		xml += xstream.toXML(funcionarios);
		
		try {
			if(!arquivoFuncionarios.exists())
				arquivoFuncionarios.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivoFuncionarios);
			gravar.print(xml);
			gravar.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> recuperarFuncionarios() {
		try {
			if(arquivoFuncionarios.exists()) {
				FileInputStream fis = new FileInputStream(arquivoFuncionarios);
				return (ArrayList<String[]>) xstream.fromXML(fis);
			}else {
				ArrayList<String[]> funcionarios = new ArrayList<>();
				this.salvarFuncionarios(funcionarios);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String[]>();
			
	}

	@Override
	public FuncionarioDTO recuperarFuncionarioPeloID(FuncionarioDTO funcionarioDTO) {
		
//		dsakodsapodksa
		return null;
	}


	
	
	
	
			
			
			
	
	
	
	
	
	
	
	
	

	
	
	
}
