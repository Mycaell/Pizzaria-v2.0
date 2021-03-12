package DAO_Imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DAO_Interfaces.ClienteInterface;
import DTO.ClienteDTO;
import View_Utilidades.ValidacaoDeSenhaEmailECampos;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public class ClienteDAO_XML implements ClienteInterface {
	
	
	
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivoClientes = new File("clientes.xml");	
	
	private ArrayList<String[]> clientes = this.recuperarClientes();
	

	@Override
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
//		cliente[0] = nome;
//		cliente[1] = sobrenome;
//		cliente[2] = cpf;
//		cliente[3] = telefone;
//		cliente[4] = bairro;
//		cliente[5] = rua;
//		cliente[6] = numero da casa;
//		cliente[7] = endereço;
//		cliente[8] = codigoDofuncionarioQueRealizouOCadastro;
//		cliente[9] = idDoPedido;
//		
//		
		
		
		ValidacaoDeSenhaEmailECampos.validarCampo(clienteDTO.getNome());
		ValidacaoDeSenhaEmailECampos.validarCampo(clienteDTO.getSobrenome());
		
		
		if(clienteDTO.getTelefone().equals("       -    ")) {
			throw new CampoVazioException();
		}
	
		if(clienteDTO.getCPF().equals("   .   .   -  ")) {
			throw new CampoVazioException();
		}

		ValidacaoDeSenhaEmailECampos.validarCampo(clienteDTO.getBairro());
		ValidacaoDeSenhaEmailECampos.validarCampo(clienteDTO.getRua());
		
//		VERIFICAR O NUMERO DA CASA
		
		
		for (String[] c : clientes) {
			if(c[2].equals(clienteDTO.getCPF())) {
				throw new ClienteExistenteException();
			}
		}
	
		String[] cliente = {clienteDTO.getNome(), clienteDTO.getSobrenome(), clienteDTO.getCPF(), clienteDTO.getTelefone(), clienteDTO.getBairro(), clienteDTO.getRua(), Integer.toString(clienteDTO.getNumeroDaCasa()), clienteDTO.getEndereço(), Integer.toString(clienteDTO.getFuncionarioQueRealizouOCadastro()), Integer.toString(clienteDTO.getIdDoCliente())};
		
		clientes.add(cliente);
		this.salvarClientes(clientes);		
	}

	@Override
	public void excluirCliente(ClienteDTO clinteDTO) {
		for (String[] cliente : clientes) {
			if(cliente[2].equals(clinteDTO.getCPF())) {
				clientes.remove(cliente);
				this.salvarClientes(clientes);
				JOptionPane.showMessageDialog(null, "Cliente Excluído!");
				break;
			}
		}			
	}

	@Override
	public ClienteDTO recuperarDadosDeTodosOsClientes() {
		ArrayList<String[]> dadosDosClientes = new ArrayList<String[]>();
		
		for (String[] cliente : clientes) {
			String[] linha = new String[4];
			linha[0] = cliente[0];
			linha[1] = cliente[2];
			linha[2] = cliente[3];
			linha[3] = cliente[7];
			
			dadosDosClientes.add(linha);
		}

		
		ClienteDTO clienteDTO = new ClienteDTO();
//		!!!!erro causado pelo iterator
//		clienteDTO.setDadosDosClientes(dadosDosClientes);
		return clienteDTO;
	}

	@Override
	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {
		for (String[] cliente : clientes) {
			if(cliente[2].equals(clienteDTO.getCPF())) {
				clienteDTO.setEndereço(cliente[7]);;
				break;
			}
		}
		return clienteDTO;		}

	@Override
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {

		if(clienteDTO.getCPF().equals("   .   .   -  ")) {
			throw new CampoVazioException();
		}
		
		for (String[] cliente : clientes) {
			if(cliente[2].equals(clienteDTO.getCPF())) {
				clienteDTO.setCpfValido(true);
				return clienteDTO;
			}
		}
		
		throw new CPFInexistenteException();
	}

	@Override
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		return null;
//		não implementado
	}
	
	
	
	public void salvarClientes(ArrayList<String[]> clientes) {
		String xml = "<?xml version =\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		xml += xstream.toXML(clientes);
		
		try {
			if(!arquivoClientes.exists())
				arquivoClientes.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivoClientes);
			gravar.print(xml);
			gravar.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> recuperarClientes() {
		try {
			if(arquivoClientes.exists()) {
				FileInputStream fis = new FileInputStream(arquivoClientes);
				return (ArrayList<String[]>) xstream.fromXML(fis);
			}else {
				ArrayList<String[]> clientes = new ArrayList<>();
				this.salvarClientes(clientes);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String[]>();			
	}
	


}
