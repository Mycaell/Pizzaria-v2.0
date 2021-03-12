package Model;

import DAO_Imple.AdapterClienteDAO_Postgress;
import DAO_Imple.InterfaceInstrucoesAlvoCliente;
import DTO.ClienteDTO;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;



public class Cliente{
	
	private String nome;
	private String sobrenome;
	private String CPF;
	private String telefone;
	private String bairro;
	private String rua;
	private int numeroDaCasa;
	private String endereço;
	private int funcionarioQueRealizouOCadastro;

//	private ClienteInterface adapterClienteDAO = new ClienteDAO_Postgre();

	private InterfaceInstrucoesAlvoCliente adapterClienteDAO = new AdapterClienteDAO_Postgress();
	
//	private AdapterClienteDAO_XML adapterClienteDAO = new AdapterClienteDAO_XML();
	
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, String sobrenome, String CPF, String telefone, String bairro, String rua, int numeroDaCasa, int funcionarioQueRealizouOCadastro) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.CPF = CPF;
		this.telefone = telefone;
		this.bairro = bairro;
		this.rua = rua;
		this.numeroDaCasa = numeroDaCasa;
		this.endereço = bairro+", "+rua+", "+numeroDaCasa;
		this.funcionarioQueRealizouOCadastro = funcionarioQueRealizouOCadastro;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumeroDaCasa() {
		return numeroDaCasa;
	}
	public void setNumeroDaCasa(int numeroDaCasa) {
		this.numeroDaCasa = numeroDaCasa;
	}
	public int getFuncionarioQueRealizouOCadastro() {
		return funcionarioQueRealizouOCadastro;
	}
	public void setFuncionarioQueRealizouOCadastro(int funcionarioQueRealizouOCadastro) {
		this.funcionarioQueRealizouOCadastro = funcionarioQueRealizouOCadastro;
	}
	
	
	
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
		adapterClienteDAO.cadastrarCliente(clienteDTO);
	}
	
	public void excluirCliente(ClienteDTO clienteDTO) {
		adapterClienteDAO.excluirCliente(clienteDTO);
	}
	
	public ClienteDTO recuperarDadosDeTodosOsClientes(){
		return adapterClienteDAO.recuperarDadosDeTodosOsClientes();
	}

	
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {
		return adapterClienteDAO.validarCPF(clienteDTO);
	}

	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {
		return adapterClienteDAO.getEndereco(clienteDTO);
	}
	
	
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO){
	
		StringBuilder stringBuilder = new StringBuilder(clienteDTO.getCPF());
		
		int tamCPF = clienteDTO.getCPF().length();
		
		if(tamCPF > 3 && tamCPF < 7) {
//			System.out.println("colocar o 1º ponto");
			stringBuilder.insert(3, ".");
//			System.out.println(stringBuilder.toString());
		}else if (tamCPF > 6 && tamCPF < 10) {
//			System.out.println("colocar o 2º ponto");
			stringBuilder.insert(3, ".");
			stringBuilder.insert(7, ".");
//			System.out.println(stringBuilder.toString());
		}else if (tamCPF > 9) {
//			System.out.println("colocar o hífen");
			stringBuilder.insert(3, ".");
			stringBuilder.insert(7, ".");
			stringBuilder.insert(11, "-");
//			System.out.println(stringBuilder.toString());
		}

		
		clienteDTO.setCPF(stringBuilder.toString());
//		System.out.println("C:"+ clienteDTO.getCPF());
		
		return adapterClienteDAO.recuperarCPFDeTodosClientes(clienteDTO);
	}
	
	
	
}
