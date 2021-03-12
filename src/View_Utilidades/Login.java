package View_Utilidades;

import DTO.FuncionarioDTO;
import View.TelaDeAtendimento;
import View.TelaDeGerente;
import View.TelaDeMotoboy;
import View.TelaDePizzaiolo;

public class Login implements InterfaceLogin{

	@Override
	public void logar(FuncionarioDTO funcionarioDTO) {
		
		int codigo = funcionarioDTO.getCodigo();

		switch(funcionarioDTO.getCargo()) {
			case "Gerente":
				new TelaDeGerente(codigo);
				break;
			case "Atendente":
				new TelaDeAtendimento(codigo);
				break;
			case "Pizzaiolo":
				new TelaDePizzaiolo(codigo);
				break;
			case "Motoboy":
				new TelaDeMotoboy(codigo);
				break;
		}
	}

}
