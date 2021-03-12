package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import View_Ouvintes.OuvinteDoOlho;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.ValidacaoDeSenhaEmailECampos;
import View_excecoes.FuncionarioExistenteException;


public class TelaDeCadastro  extends TelaPadrao {

	private JLabel L;
	private JTextField nome;
	private JTextField sobrenome;
	private char sexo = 'F';
	private JFormattedTextField dataDeNascimento;
	private JTextField endereço;
	private JFormattedTextField telefone;
	private JFormattedTextField CPF;
	private JTextField email;
	private JPasswordField senha;
	private JPasswordField confirmacaoDeSenha;
	
	private JComboBox<Object> funcionarios;

	private JButton botaoSalvar;
	private JButton botaoCancelar;
	
	private JLabel olho;
	private JTextField senhaRevelada;
	
	private JRadioButton radioButtonF;
	private JRadioButton radioButtonM;
	


	
	private JLabel labelSenha;
	private JLabel labelConfimacaoSenha;
	
	private int codigoDoGerenteLogado;
	
	
	
	public JLabel getLabelCentral() {
		return L;
	}


	public void setLabelCentral(JLabel labelCentral) {
		this.L = labelCentral;
	}


	public JPasswordField getSenha() {
		return senha;
	}


	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}


	public JPasswordField getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}


	public void setConfirmacaoDeSenha(JPasswordField confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}


	public JTextField getSenhaRevelada() {
		return senhaRevelada;
	}


	public void setSenhaRevelada(JTextField senhaRevelada) {
		this.senhaRevelada = senhaRevelada;
	}


	public JTextField getNome() {
		return nome;
	}


	public void setNome(JTextField nome) {
		this.nome = nome;
	}


	public JTextField getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(JTextField sobrenome) {
		this.sobrenome = sobrenome;
	}


	public JFormattedTextField getDataDeNascimento() {
		return dataDeNascimento;
	}


	public void setDataDeNascimento(JFormattedTextField dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}


	public JTextField getEndereço() {
		return endereço;
	}


	public void setEndereço(JTextField endereço) {
		this.endereço = endereço;
	}


	public JFormattedTextField getTelefone() {
		return telefone;
	}


	public void setTelefone(JFormattedTextField telefone) {
		this.telefone = telefone;
	}


	public JFormattedTextField getCPF() {
		return CPF;
	}


	public void setCPF(JFormattedTextField cPF) {
		CPF = cPF;
	}


	public JTextField getEmail() {
		return email;
	}


	public void setEmail(JTextField email) {
		this.email = email;
	}


	public JComboBox<Object> getFuncionarios() {
		return funcionarios;
	}


	public void setFuncionarios(JComboBox<Object> funcionarios) {
		this.funcionarios = funcionarios;
	}


	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}


	public void setBotaoSalvar(JButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}


	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}


	public void setBotaoCancelar(JButton botaoCancelar) {
		this.botaoCancelar = botaoCancelar;
	}


	public JLabel getOlho() {
		return olho;
	}


	public void setOlho(JLabel olho) {
		this.olho = olho;
	}


	public JRadioButton getRadioButtonF() {
		return radioButtonF;
	}


	public void setRadioButtonF(JRadioButton radioButtonF) {
		this.radioButtonF = radioButtonF;
	}


	public JRadioButton getRadioButtonM() {
		return radioButtonM;
	}


	public void setRadioButtonM(JRadioButton radioButtonM) {
		this.radioButtonM = radioButtonM;
	}
	public JLabel getLabelSenha() {
		return labelSenha;
	}


	public void setLabelSenha(JLabel labelSenha) {
		this.labelSenha = labelSenha;
	}


	public JLabel getLabelConfimacaoSenha() {
		return labelConfimacaoSenha;
	}


	public void setLabelConfimacaoSenha(JLabel labelConfimacaoSenha) {
		this.labelConfimacaoSenha = labelConfimacaoSenha;
	}

	public TelaDeCadastro(int codigoDoGerenteLogado) {
		super("Cadastro");
		this.setSize(800, 430);
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;


		
		adicionarLabels();
		adicionarBotoes();
		adicionarFields();
		adicionarMascaras();
	
		this.setVisible(true);
	}
	

	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		adicionarBotoes();
//		adicionarFields();
//		adicionarMascaras();
//		
//	}
	
	

	private void adicionarLabels() {
		
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	

		
		L = AdicionadorDeComponentes.adicionarJLabel(this, "CADASTRO", 195, 40, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
//		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 440, 135, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sexo", 110, 165, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Data de Nasc", 430, 165, 145, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Endereço", 110, 195, 85, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 110, 225, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 500, 225, 75, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "E-mail", 110, 255, 55, 20);
		labelSenha = AdicionadorDeComponentes.adicionarJLabel(this, "Senha", 110, 285, 55, 20);
		labelConfimacaoSenha= AdicionadorDeComponentes.adicionarJLabel(this, "Confirme a Senha", 110, 315, 160, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Cargo", 490, 285, 55, 23);
		
		
		
		
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);

//		GerenteController gerenteController = new GerenteController();
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		
//		if(gerenteController.recuperarCargo(funcionarioDTO).getCargo() == null) {
//			AdicionadorDeComponentes.adicionarJLabel(this, "<html><u> Direção (Direção : "+codigoDoGerenteLogado+")</u></html>", 5, 252, 400, 20);
//		}else {
//			AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 252, 400, 20);
//		}
//	
	}



	private void adicionarBotoes() {
		botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 300, 365, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(new OuvinteBotaoSalvar(this));
		
		botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 400, 365, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(new OuvinteBotaoCancelar(this));
		
		radioButtonF = new JRadioButton("F");
		radioButtonF.setBounds(170, 165, 40, 20);
		radioButtonM = new JRadioButton("M");
		radioButtonM.setBounds(210, 165, 45, 20);

	    ButtonGroup grupoRadioSexo = new ButtonGroup();
	    grupoRadioSexo.add(radioButtonF);
	    grupoRadioSexo.add(radioButtonM);
	    
	    radioButtonF.setSelected(true);
	    
	    radioButtonF.setBackground(null);
	    radioButtonM.setBackground(null);
	    
	    add(radioButtonF);
	    add(radioButtonM);
	    
	    ImageIcon imgOlho = Icones.ICONE_OLHO_FECHADO;
	    olho = new JLabel(imgOlho);
	    olho.setToolTipText("revelar senha");
	    olho.setBounds(285, 285, 30, 20);
	    add(olho);
	    
	    senha = AdicionadorDeComponentes.adicionarJPasswordField(this, 170, 285, 110, 20);
	    senhaRevelada = AdicionadorDeComponentes.adicionarJTextField(this, 170, 285,110, 20);
	    senhaRevelada.setVisible(false);
	    olho.addMouseListener(new OuvinteDoOlho(this,senha,senhaRevelada,olho));
	    
	    ArrayList<String> cargos = new ArrayList<String>();
	    
	    cargos.add("Gerente");
// 666 É O CÓDIGO DO DONO DO SISTEMA ( CADASTRANDO O PRIMEIRO GERENTE DO SISTEMA)
	    if(codigoDoGerenteLogado != 666) {
	    	cargos.add("Atendente");
	    	cargos.add("Pizzaiolo");
	    	cargos.add("Motoboy");
	    }
	    
	    funcionarios = new JComboBox<>(cargos.toArray());
	    funcionarios.setBounds(550, 285, 115, 20);
	    add(funcionarios);
	}
	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 170, 135, 110, 20);
		sobrenome = AdicionadorDeComponentes.adicionarJTextField(this, 550, 135, 115, 20);
		endereço = AdicionadorDeComponentes.adicionarJTextField(this, 200, 195, 190, 20);
		email = AdicionadorDeComponentes.adicionarJTextField(this, 170, 255, 220, 20);
		email.setText("gmail");
		email.addFocusListener(new OuvinteDeFocoDoEmail(this));
		confirmacaoDeSenha = AdicionadorDeComponentes.adicionarJPasswordField(this, 280	, 315, 110, 20);
	}

	private void adicionarMascaras() {
		try {
			dataDeNascimento = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "##/##/####", 550, 165, 115, 20);
			CPF = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 550, 225, 115, 20);
			telefone = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "(##) ####-####", 200, 225, 110, 20);
		}catch (ParseException e) {

		}
	}
	
	private class OuvinteBotaoCancelar implements ActionListener {

		private TelaDeCadastro telaDeCadastro;
		
		public OuvinteBotaoCancelar(TelaDeCadastro telaDeCadastro) {
			this.telaDeCadastro = telaDeCadastro;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(codigoDoGerenteLogado != 666) {
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
				telaDeFuncionarios.setLocationRelativeTo(telaDeCadastro);
			}else {
				TelaDeLogin telaDeLogin = new TelaDeLogin();
				telaDeLogin.setLocationRelativeTo(telaDeCadastro);
			}
			
			telaDeCadastro.dispose();
			
		}
		
	}
	
	private class OuvinteDeFocoDoEmail implements FocusListener{

		private TelaDeCadastro telaDeCadastro;
		
		public OuvinteDeFocoDoEmail(TelaDeCadastro telaDeCadastro) {
			this.telaDeCadastro = telaDeCadastro;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			email.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(email.getText().equals("")) {
				email.setText("gmail");
			}
			
		}
		
	}	
	
	private class OuvinteBotaoSalvar implements ActionListener{
		
		private TelaDeCadastro telaDeCadastro;
		
		public OuvinteBotaoSalvar(TelaDeCadastro telaDeCadastro) {
			this.telaDeCadastro = telaDeCadastro;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if(!radioButtonF.isSelected()) {
				sexo = 'M';
			}
			
			
			if(ValidacaoDeSenhaEmailECampos.validarDadosDoUsuario(telaDeCadastro, nome, sobrenome, dataDeNascimento, endereço, telefone, CPF, email) && ValidacaoDeSenhaEmailECampos.validarSenhaEConfirmacaoDeSenha(telaDeCadastro, senha, confirmacaoDeSenha)) {

				
				String cargoSelecionado = (String) funcionarios.getSelectedItem();

				
				GerenteController gerenteController = new GerenteController();
				
				Date dataDeNasc = null;
				try {
					SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					dataDeNasc = formataData.parse(dataDeNascimento.getText());
				}catch (ParseException er) {
				}

				
//				int codigo = gerenteController.recuperarDadosDeTodosFuncionarios().getDadosDosFuncionarios().size() + 1;
				
				try {
					gerenteController.cadastrarFuncionario(new FuncionarioDTO(nome.getText(),sobrenome.getText(),sexo,dataDeNasc,endereço.getText(),telefone.getText(),CPF.getText(),email.getText(),new String(senha.getPassword()),cargoSelecionado, codigoDoGerenteLogado));
					JOptionPane.showMessageDialog(telaDeCadastro, "Cadastrado realizado com sucesso!");
					
					if(codigoDoGerenteLogado != 666) {
						TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
						telaDeFuncionarios.setLocationRelativeTo(telaDeCadastro);
					}else {
						TelaDeLogin telaDeLogin = new TelaDeLogin();
						telaDeLogin.setLocationRelativeTo(telaDeCadastro);
					}
					
					telaDeCadastro.dispose();
					
				}catch (FuncionarioExistenteException er) {
					JOptionPane.showMessageDialog(telaDeCadastro, er.getMessage(),  "E-mail inválido", JOptionPane.ERROR_MESSAGE);
				}

				
			}
		}
	}


	

}
