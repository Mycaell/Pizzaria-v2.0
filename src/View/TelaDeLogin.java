package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.FactoryTelaInicial;
import View_Utilidades.ValidacaoDeSenhaEmailECampos;
import View_excecoes.CampoVazioException;
import View_excecoes.FuncionarioNaoExistenteException;

public class TelaDeLogin extends TelaPadrao{

	private JTextField email;
	private JPasswordField senha;

	
	public TelaDeLogin() {
		super("Login");
		this.setSize(800, 420);	
		
		adicionarLabels();
		adicionarBotoes();
		adicionarFields();

		this.setVisible(true);
	
	}	


	
	
	private void adicionarLabels() {
		
		JLabel label = new JLabel(Icones.FUNDO_LOGIN);
		label.setBounds(0, 0, 800, 400);
		setContentPane(label);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "E-mail", 195, 155, 60, 30);		
		AdicionadorDeComponentes.adicionarJLabel(this, "Senha",195, 195, 60, 30);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Estado",195, 245, 65, 30);
		
	}
	
	private void adicionarBotoes() {
		JButton botaoEntrar = AdicionadorDeComponentes.adicionarJButton(this, "Entrar", 470, 250, 70, 20);
		botaoEntrar.addActionListener(new OuvinteTelaDeLogin(this));
		
		JButton botaoCadastrar = AdicionadorDeComponentes.adicionarJButton(this, "Cadastrar-se", 410, 300, 130, 20);
		botaoCadastrar.setIcon(Icones.ICONE_USUARIO);
		botaoCadastrar.addActionListener(new OuvinteTelaDeLogin(this));
		
//		CRIAR UMA TABELA NO BANCO CHAMADA ESTADO PARA CONTER TODOS OS ESTADOS EM QU E PIZZARIA POSSUI UMA FILIAL PARA ASSIM ENTÃO PREENCHER ESSE COMBOBOX
//		estados = new JComboBox<>(new String[] {"PB", "PE", "RJ", "SP"});
//		estados.setBounds(260, 250, 50, 20);
//		add(estados);
		
		
		
	}
	
	private void adicionarFields() {
		email = AdicionadorDeComponentes.adicionarJTextField(this, 260, 160, 280, 20);
		senha = AdicionadorDeComponentes.adicionarJPasswordField(this, 260, 200, 280, 20);
	}
	
	private class OuvinteTelaDeLogin implements ActionListener {

		
		private TelaDeLogin telaDeLogin;
		
		public OuvinteTelaDeLogin(TelaDeLogin telaDeLogin) {
			this.telaDeLogin = telaDeLogin;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Cadastrar-se")){
				String codigoDeAcesso = JOptionPane.showInputDialog(telaDeLogin,"Digite o código de acesso","Código de Acesso",JOptionPane.QUESTION_MESSAGE);
				
				if(codigoDeAcesso != null) {
					if(codigoDeAcesso.equals("chave mestre")) {
//						esse parâmetro é o código do dono do SISTEMA
						TelaDeCadastro  telaDeCadastro = new TelaDeCadastro(666);
						telaDeCadastro.setLocationRelativeTo(telaDeLogin);
						telaDeLogin.dispose();
					}else {
						JOptionPane.showMessageDialog(telaDeLogin, "Código inválido!", "Erro!", JOptionPane.ERROR_MESSAGE); 		    	
					}	
				}
				
			}else if(botao.equals("Entrar")) {

				try {
					
					String emailDigitado = email.getText();
					String senhaDigitada = new String(senha.getPassword());
					
//					validando se os campos estao vazios
					ValidacaoDeSenhaEmailECampos.validarCampo(emailDigitado);
					ValidacaoDeSenhaEmailECampos.validarCampo(senhaDigitada);
					
//					!!!!!
//					!!!!!

					
					
					
					GerenteController gerenteController = new GerenteController();
					
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setEmail(emailDigitado);
					funcionarioDTO.setSenha(senhaDigitada);
					
				
					
					
					
					funcionarioDTO = gerenteController.recuperarFuncionario(funcionarioDTO);
					
					String cargo = funcionarioDTO.getCargo();

					
					if(cargo != null) {
						FactoryTelaInicial factory = new FactoryTelaInicial();
						factory.logar(cargo, funcionarioDTO.getCodigo());
						
						telaDeLogin.dispose();
					}else {
						JOptionPane.showMessageDialog(telaDeLogin, "Você não está cadastrado!", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					}
					
					

					
					
					
					
					
					
					
					
					
//					testes
					
//					funcionarioDTO = gerenteController.recuperarFuncionario(funcionarioDTO);
//					
//					String cargo = funcionarioDTO.getCargo();
//					
//					
//					if(cargo != null) {
//						
//						int codigoDoFuncionarioLogado = gerenteController.recuperarCodigo(funcionarioDTO).getCodigo();
//						if(cargo.equals("Gerente")) {
//							TelaDeGerente telaDeGerente = new TelaDeGerente(codigoDoFuncionarioLogado);
//							telaDeGerente.setLocationRelativeTo(telaDeLogin);
//							telaDeLogin.dispose();
//							
//						}else if(cargo.equals("Atendente")) {
//							TelaDeAtendimento telaDeAtendente = new TelaDeAtendimento(codigoDoFuncionarioLogado);
//							telaDeAtendente.setLocationRelativeTo(telaDeLogin);
//							telaDeLogin.dispose();
//							
//						}else if(cargo.equals("Pizzaiolo")) {
//							TelaDePizzaiolo telaDePizzaiolo = new TelaDePizzaiolo(codigoDoFuncionarioLogado);
//							telaDePizzaiolo.setLocationRelativeTo(telaDeLogin);
//							telaDeLogin.dispose();
//						}else if(cargo.equals("Motoboy")) {
//							TelaDeMotoboy telaDeMotoboy = new TelaDeMotoboy(codigoDoFuncionarioLogado);
//							telaDeMotoboy.setLocationRelativeTo(telaDeLogin);
//							telaDeLogin.dispose();
//						}
//					}else {
////						System.out.println("idiota");
////						exibir JOPPAne com msg de erro
//					}
						
						
				}catch (CampoVazioException er) {
					JOptionPane.showMessageDialog(telaDeLogin,er.getMessage(),"Campo vázio",JOptionPane.ERROR_MESSAGE);
				}catch (FuncionarioNaoExistenteException e1) {
				JOptionPane .showMessageDialog(telaDeLogin, e1.getMessage());
				}
				
				
				
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		adicionarBotoes();
//		adicionarFields();
//	}

	
	
	
	
	public static void main(String[] args) {
		new TelaDeLogin();
	}





	
}
