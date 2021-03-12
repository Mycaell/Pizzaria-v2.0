package View_Utilidades;

import View.TelaDeAtendimento;
import View.TelaDeGerente;
import View.TelaDeMotoboy;
import View.TelaDePizzaiolo;

public class FactoryTelaInicial {

	public void logar(String cargo, int codigo) {

		switch(cargo) {
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
