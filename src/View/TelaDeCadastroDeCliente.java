package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.AtendenteController;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public class TelaDeCadastroDeCliente extends TelaComMenu{

	
	private JTextField nome;
	private JTextField sobrenome;
	private JFormattedTextField telefone;
	private JFormattedTextField CPF;
	private JTextField bairro;
	private JTextField rua;
	private JTextField numDaCasa;
	
	private int codigoDoAtendenteLogado;
	
	
	private TelaDeAtendimento telaDeAtendimento;
	
	
	
//	ao invés de se passar a telaDeAtendimento inteira poderia-se passar apensa o JTexfild referente ao cpf
	public TelaDeCadastroDeCliente(int codigoDoAtendenteLogado, TelaDeAtendimento telaDeAtendimento) {
		super("Cadastramento de Cliente");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.codigoDoAtendenteLogado = codigoDoAtendenteLogado;
		this.telaDeAtendimento = telaDeAtendimento;
		

		adicionarLabels();
		adicionarFields();
		adicionarBotoes();	
	
		
		this.setVisible(true);
		
	}
	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		adicionarFields();
//		adicionarBotoes();	
//				
//	}
	


	private void adicionarLabels() {
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CADASTRO", 195, 40, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 440, 135, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 110, 180, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 505, 180, 75, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Bairro", 110, 225, 70, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Rua", 110, 270, 41, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Nº da casa", 450, 225, 95, 20);
		
//	
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//		
	}

	
	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 170, 135, 210, 20);
		sobrenome = AdicionadorDeComponentes.adicionarJTextField(this, 550, 135, 200, 20);
		
		try {
			telefone = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "(##) ####-####", 190, 180, 90, 20);
			CPF = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 550, 180, 100, 20);
		}catch (ParseException e) {
		}
		
		
		bairro = AdicionadorDeComponentes.adicionarJTextField(this, 170, 225, 210, 20);
		rua = AdicionadorDeComponentes.adicionarJTextField(this, 170, 270, 222, 20);
		numDaCasa = AdicionadorDeComponentes.adicionarJTextField(this, 550, 225, 50, 20);
	}
	
	
	private void adicionarBotoes() {
		
		OuvinteTelaDeCadastroDeCliente ouvinte = new OuvinteTelaDeCadastroDeCliente(this);
//		JButton BTvoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 110, 350, 90, 20);
//		BTvoltar.setIcon(Icones.ICONE_VOLTAR);
//		BTvoltar.addActionListener(ouvinte);

		JButton BTlimparCampos = AdicionadorDeComponentes.adicionarJButton(this, "Limpar Campos", 110, 350, 145, 20);
		BTlimparCampos.setIcon(Icones.ICONE_VASSOURA);
		BTlimparCampos.addActionListener(ouvinte);
		
		JButton BTsalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 560, 350, 90, 20);
		BTsalvar.setIcon(Icones.ICONE_SALVAR);
		BTsalvar.addActionListener(ouvinte);
	}

	private class OuvinteTelaDeCadastroDeCliente implements ActionListener{

		private TelaDeCadastroDeCliente janela;
		
		public OuvinteTelaDeCadastroDeCliente(TelaDeCadastroDeCliente janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Salvar")) {
				
				AtendenteController atendenteController = new AtendenteController();
					
				try {
					
					if(numDaCasa.getText().equals("")) {
						throw new CampoVazioException();
					}
					
					
//					int idDoCliente = new GerenteController().recuperarDadosDeTodosOsClientes().getDadosDosClientes().size() + 1;
					
					ClienteDTO clienteDTO = new ClienteDTO(nome.getText(), sobrenome.getText(), CPF.getText(), telefone.getText(), bairro.getText(), rua.getText(), Integer.parseInt(numDaCasa.getText()), codigoDoAtendenteLogado);
					
					atendenteController.cadastrarCliente(clienteDTO);

					JOptionPane.showMessageDialog(janela, "Cliente Cadastrado!");
					
//					TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoAtendenteLogado);
//					telaDeAtendimento.setLocationRelativeTo(janela);
//					
					telaDeAtendimento.getCpf().setText(CPF.getText());

					janela.dispose();
					
				}catch (CampoVazioException er) {
					JOptionPane.showMessageDialog(janela, er.getMessage(), "Campo vazio", JOptionPane.ERROR_MESSAGE);
				}catch (ClienteExistenteException er) {
					JOptionPane.showMessageDialog(janela, er.getMessage(), "CPF inválido", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException er) {
					JOptionPane.showMessageDialog(janela, "O Nº da casa deve ser preenchido apenas com números!", "Nº da casa inválido", JOptionPane.ERROR_MESSAGE);
				}
				
			}
//			else if(botao.equals("Voltar")) {
//				TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoAtendenteLogado);
//				telaDeAtendimento.setLocationRelativeTo(janela);
//				janela.dispose();
//			}
			else {
				nome.setText("");
				sobrenome.setText("");
				telefone.setText("");
				CPF.setText("");
				bairro.setText("");
				rua.setText("");
				numDaCasa.setText("");
			}
			
		}
		
	}



	
	
}
