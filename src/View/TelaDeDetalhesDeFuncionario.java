package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeDetalhesDeFuncionario extends TelaDeCadastro {

	
	private int codigoDoGerenteLogado;
	private int codigoDoFuncionario;

	
	public TelaDeDetalhesDeFuncionario(int codigoDoGerenteLogado, int codigoDoFuncionario) {
		super(codigoDoGerenteLogado);

		this.setTitle("Detalhes");
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.codigoDoFuncionario = codigoDoFuncionario;
		
		
		preencerCampos();
		adicionarComponentes();
		removerComponentes();
	}

	private void removerComponentes() {
		super.remove(getBotaoCancelar());
		super.remove(getBotaoSalvar());
		super.remove(getSenha());
		super.remove(getSenhaRevelada());
		super.remove(getOlho());
		super.remove(getConfirmacaoDeSenha());
		super.remove(getOlho());
		super.remove(getLabelConfimacaoSenha());
		super.remove(getLabelSenha());
		super.remove(getLabelCentral());
		repaint();
	}

	private void adicionarComponentes() {
		
		JLabel labelCentral = AdicionadorDeComponentes.adicionarJLabel(this, "DETALHES", 190, 40, 450, 60);
		labelCentral.setFont(new Font("Times new Roman", Font.BOLD, 75));
		labelCentral.setForeground(Color.DARK_GRAY);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "ID", 145, 285, 20, 20);
		
		JTextField campoID = AdicionadorDeComponentes.adicionarJTextField(this, 170, 285, 60, 20);
		campoID.setText(Integer.toString(codigoDoFuncionario));
		
		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 100, 365, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new Ouvinte(this));
	}

	private void preencerCampos() {
		
		GerenteController gerenteController = new GerenteController();
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoFuncionario);
		
		funcionarioDTO = gerenteController.recuperarFuncionarioPeloID(funcionarioDTO);
		
		super.getNome().setText(funcionarioDTO.getNome());
		
		super.getSobrenome().setText(funcionarioDTO.getSobrenome());

		char sexo = funcionarioDTO.getSexo();
		if(sexo == 'F') {
			getRadioButtonF().setSelected(true);
		}else {
			getRadioButtonM().setSelected(true);
		}
		
		super.getEndereço().setText(funcionarioDTO.getEndereço());
		
		
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		super.getDataDeNascimento().setText(formataData.format(funcionarioDTO.getDataDeNascimento()));

		
		super.getTelefone().setText(funcionarioDTO.getTelefone());
		super.getCPF().setText(funcionarioDTO.getCPF());
		super.getEmail().setText(funcionarioDTO.getEmail());
		super.getFuncionarios().setSelectedItem(funcionarioDTO.getCargo());

		
		
	}

	private class Ouvinte implements ActionListener{

		private TelaDeDetalhesDeFuncionario telaDeDetalhesDeFuncionario;
		
		public Ouvinte(TelaDeDetalhesDeFuncionario telaDeDetalhesDeFuncionario) {
			this.telaDeDetalhesDeFuncionario = telaDeDetalhesDeFuncionario;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
			telaDeFuncionarios.setLocationRelativeTo(telaDeDetalhesDeFuncionario);
			telaDeDetalhesDeFuncionario.dispose();
		}
		
	}
	
	
	
	
	
}

